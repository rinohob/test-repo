package com.shrss.core.context;

import static org.apache.sling.testing.mock.caconfig.ContextPlugins.CACONFIG;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.impl.ResourceTypeBasedResourcePicker;
import org.apache.sling.models.spi.ImplementationPicker;
import org.apache.sling.testing.mock.sling.ResourceResolverType;

import com.adobe.cq.export.json.SlingModelFilter;
import com.adobe.cq.wcm.core.components.internal.link.DefaultPathProcessor;
import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.msm.api.MSMNameConstants;
import com.google.common.collect.ImmutableMap;

import io.wcm.testing.mock.aem.MockExternalizer;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextBuilder;

/**
 * Provides a context for unit tests.
 */
@SuppressWarnings("deprecation")
public final class CoreComponentTestContext {

	public static final String TEST_CONTENT_JSON = "/test-content.json";
	public static final String TEST_TAGS_JSON = "/test-tags.json";
	public static final String TEST_CONTENT_DAM_JSON = "/test-content-dam.json";
	public static final String TEST_APPS_JSON = "/test-apps.json";
	public static final String TEST_CONF_JSON = "/test-conf.json";

	public static final String EXTERNALIZER_PUBLISH_DOMAIN = "https://example.org";

	private CoreComponentTestContext() {
		// only static methods
	}

	private static final ImmutableMap<String, Object> PROPERTIES = ImmutableMap.of("resource.resolver.mapping",
			ArrayUtils.toArray("/:/", "^/content/links/site1/(.+)</content/site1/$1"));

	public static AemContext newAemContext() {
		return new AemContextBuilder().plugin(CACONFIG).resourceResolverType(ResourceResolverType.JCR_MOCK)
				.resourceResolverFactoryActivatorProps(PROPERTIES).<AemContext>afterSetUp(context -> {
					context.registerService(SlingModelFilter.class, new MockSlingModelFilter() {
						private final Set<String> IGNORED_NODE_NAMES = new HashSet<String>() {
							{
								add(NameConstants.NN_RESPONSIVE_CONFIG);
								add(MSMNameConstants.NT_LIVE_SYNC_CONFIG);
								add("cq:annotations");
							}
						};

						@Override
						public Map<String, Object> filterProperties(Map<String, Object> map) {
							return map;
						}

						@Override
						public Iterable<Resource> filterChildResources(Iterable<Resource> childResources) {
							return StreamSupport.stream(childResources.spliterator(), false)
									.filter(r -> !IGNORED_NODE_NAMES.contains(r.getName()))
									.collect(Collectors.toList());
						}
					});
					context.registerService(ImplementationPicker.class, new ResourceTypeBasedResourcePicker());
					Optional.ofNullable(context.getService(Externalizer.class)).ifPresent((externalizer -> {
						if (externalizer instanceof MockExternalizer) {
							((MockExternalizer) externalizer).setMapping(Externalizer.PUBLISH,
									EXTERNALIZER_PUBLISH_DOMAIN);
						}
					}));
					context.registerInjectActivateService(new DefaultPathProcessor(),
							ImmutableMap.of("vanityConfig", DefaultPathProcessor.VanityConfig.ALWAYS.getValue()));
				}).build();
	}
}

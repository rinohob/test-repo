package com.shrss.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;
import com.shrss.core.services.TagsPathMappingConfigService;

/**
 * The Class TagsPathMappingConfigServiceImpl.
 */
@Component(service = TagsPathMappingConfigService.class, immediate = true, configurationPid = "com.shrss.core.services.TagsPathMappingConfigService")
public class TagsPathMappingConfigServiceImpl implements TagsPathMappingConfigService {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(TagsPathMappingConfigServiceImpl.class);

	/** The map. */
	Map<String, String> map = new HashMap<>();

	/** The Constant TAG_PATH_GENERIC_LIST_PATH. */
	private static final String TAG_PATH_GENERIC_LIST_PATH = "/etc/acs-commons/lists/path-tags-mapping/jcr:content/list";

	/** The Constant TITLE. */
	

	/** The Constant VALUE. */
	private static final String VALUE = "value";

	/** The resolver factory. */
	@Reference
	ResourceResolverFactory resolverFactory;

	/**
	 * Activate.
	 */
	@Activate
	protected void activate() {
		map = getTagsPathMapping();
	}

	/**
	 * Gets the tags path mapping.
	 *
	 * @return the tags path mapping
	 */
	@Override
	public Map<String, String> getTagsPathMapping() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "readService");
		Map<String, String> hashmap = new HashMap<>();
		try (ResourceResolver resourceResolver = resolverFactory.getServiceResourceResolver(param)){
			Resource resource = resourceResolver.getResource(TAG_PATH_GENERIC_LIST_PATH);
			if (Objects.isNull(resource)) {
				LOG.info("No Generic list is found on path {}", TAG_PATH_GENERIC_LIST_PATH);
				return hashmap;
			}
			Iterable<Resource> children = resource.getChildren();
			for (Resource childResource : children) {
				String path = childResource.getValueMap().get(JcrConstants.JCR_TITLE, String.class);
				String tag = childResource.getValueMap().get(VALUE, String.class);
				hashmap.put(path, tag);
			}
			
		} catch (LoginException le) {
			LOG.error("LoginException", le);
		} catch (Exception e) {
			LOG.error("LoginException", e);
		} 
		return hashmap;
	}

	/**
	 * Gets the all tags by path.
	 *
	 * @param path the path
	 * @return the all tags by path
	 */
	@Override
	public List<String> getAllTagsByPath(String path) {
		List<String> tags = new ArrayList<>();
		for (String key : map.keySet()) {
			if (path.contains(key)) {
				String tag = map.get(key);
				if (null != tag) {
					tags.add(tag);
				}
			}
		}
		return tags;
	}
}

package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Locale;

import javax.json.Json;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceWrapper;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.models.factory.ModelFactory;
import org.apache.sling.servlethelpers.MockRequestPathInfo;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.components.ComponentContext;
import com.day.cq.wcm.api.components.EditContext;
import com.day.cq.wcm.api.designer.Style;
import com.day.cq.wcm.scripting.WCMBindingsConstants;
import com.shrss.core.context.CoreComponentTestContext;
import com.shrss.core.models.Vimeo;
import com.shrss.core.utils.Utils;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(AemContextExtension.class)
class VimeoImplTest {

	private static final String CONTENT_ROOT = "/content";
	private static final String ROOT_PAGE = "/content/vimeo";
	private static final String GRID = ROOT_PAGE + "/jcr:content/root/responsivegrid";
	private static final String VIDEO_1 = "/video1";
	private static final String VIDEO_2 = "/video2";
	private static final String VIDEO_3 = "/video3";
	private static final String PATH_VIDEO_1 = GRID + VIDEO_1;
	private static final String PATH_VIDEO_2 = GRID + VIDEO_2;
	private static final String PATH_VIDEO_3 = GRID + VIDEO_3;

	private final AemContext context = CoreComponentTestContext.newAemContext();

	@Mock
	private Style style;

	@Mock
	private Page page;

	/** The component context. */
	@Mock
	private ComponentContext componentContext;

	@Mock
	private ComponentContext parentComponentContext;

	@Mock
	private EditContext editContext;

	@BeforeEach
	void setUp() {
		context.load().json("/com/shrss/core/models/video/video.json", CONTENT_ROOT);
		style = mock(Style.class);
		// by default all parameters configurable and by enabled
		Mockito.lenient().when(style.get(Vimeo.PN_DESIGN_AUTOPLAY_ENABLED, false)).thenReturn(true);
		Mockito.lenient().when(style.get(Vimeo.PN_DESIGN_AUTOPLAY_DEFAULT_VALUE, false)).thenReturn(true);
		Mockito.lenient().when(style.get(Vimeo.PN_DESIGN_MUTE_ENABLED, false)).thenReturn(true);
		Mockito.lenient().when(style.get(Vimeo.PN_DESIGN_MUTE_DEFAULT_VALUE, false)).thenReturn(true);
		Mockito.lenient().when(style.get(Vimeo.PN_DESIGN_LOOP_ENABLED, false)).thenReturn(true);
		Mockito.lenient().when(style.get(Vimeo.PN_DESIGN_LOOP_DEFAULT_VALUE, false)).thenReturn(true);
		Mockito.lenient().when(style.get(Vimeo.PN_DESIGN_PLAYS_INLINE_ENABLED, false)).thenReturn(true);
		Mockito.lenient().when(style.get(Vimeo.PN_DESIGN_PLAYS_INLINE_DEFAULT_VALUE, false)).thenReturn(true);
		Mockito.lenient().when(componentContext.getParent()).thenReturn(parentComponentContext);
	}

	@Test
	void testWithDefaultValues() throws URISyntaxException {
		Mockito.when(page.getLanguage()).thenReturn(Locale.US);
		VimeoImpl vimeo = getVimeoUnderTest(PATH_VIDEO_1, page);
		assertEquals(new URI(
				"https://player.vimeo.com/video/9389973?origin=http%3A%2F%2Flocalhost&hl=en_US&muted=1&autoplay=1&loop=1&playlist=9389973&playsinline=1"),
				vimeo.getIFrameSrc(style).get());
		assertEquals("300", vimeo.getIFrameWidth());
		assertEquals("200", vimeo.getIFrameHeight());
		assertEquals("56.25", vimeo.getIFrameAspectRatio());
		assertEquals("responsive", vimeo.getVimeoLayout());
		assertEquals("Vimeo Video", vimeo.getVimeoAccessibilityLabel());
	}

	@Test
	void testWithDefaultValuesInEditMode() throws URISyntaxException {
		Mockito.when(page.getLanguage()).thenReturn(Locale.US);
		Mockito.when(parentComponentContext.getEditContext()).thenReturn(editContext);
		VimeoImpl vimeo = getVimeoUnderTest(PATH_VIDEO_1, page);
		// autoplay should not be set in edit mode
		assertEquals(new URI(
				"https://player.vimeo.com/video/9389973?origin=http%3A%2F%2Flocalhost&hl=en_US&muted=1&loop=1&playlist=9389973&playsinline=1"),
				vimeo.getIFrameSrc(style).get());
		assertEquals("300", vimeo.getIFrameWidth());
		assertEquals("200", vimeo.getIFrameHeight());
		assertEquals("56.25", vimeo.getIFrameAspectRatio());
		assertEquals("responsive", vimeo.getVimeoLayout());
	}

	@Test
	void testWithOverriddenValues() throws URISyntaxException {
		Mockito.when(page.getLanguage()).thenReturn(Locale.GERMANY);
		VimeoImpl vimeo = getVimeoUnderTest(PATH_VIDEO_2, page);
		assertEquals(new URI(
				"https://player.vimeo.com/video/9389973?origin=http%3A%2F%2Flocalhost&hl=de_DE&muted=0&autoplay=0&loop=0&playsinline=0"),
				vimeo.getIFrameSrc(style).get());
		assertNull(vimeo.getIFrameWidth());
		assertNull(vimeo.getIFrameHeight());
		assertNull(vimeo.getIFrameAspectRatio());
		assertNull(vimeo.getVimeoLayout());
		assertEquals("Test Video", vimeo.getVimeoAccessibilityLabel());
	}

	@Test
	void testWithOverriddenValuesWithoutStyle() throws URISyntaxException {
		Mockito.when(page.getLanguage()).thenReturn(Locale.GERMANY);
		VimeoImpl vimeo = getVimeoUnderTest(PATH_VIDEO_2, page);
		assertEquals("https://player.vimeo.com/video/9389973?origin=http%3A%2F%2Flocalhost&hl=de_DE",
				vimeo.getIFrameSrc());
		assertNull(vimeo.getIFrameWidth());
		assertNull(vimeo.getIFrameHeight());
		assertNull(vimeo.getIFrameAspectRatio());
		assertNull(vimeo.getVimeoLayout());
	}

	@Test
	void testWithAllOptionalFeaturesDisabled() throws URISyntaxException {
		Mockito.when(page.getLanguage()).thenReturn(Locale.US);
		VimeoImpl vimeo = getVimeoUnderTest(PATH_VIDEO_2, page);
		assertEquals(new URI(
				"https://player.vimeo.com/video/9389973?origin=http%3A%2F%2Flocalhost&hl=en_US&muted=0&autoplay=0&loop=0&playsinline=0"),
				vimeo.getIFrameSrc(style).get());
		assertNull(vimeo.getIFrameWidth());
		assertNull(vimeo.getIFrameHeight());
		assertNull(vimeo.getIFrameAspectRatio());
		assertNull(vimeo.getVimeoLayout());
	}

	@Test
	void testWithNoId() throws URISyntaxException {
		VimeoImpl vimeo = getVimeoUnderTest(PATH_VIDEO_3, page);
		assertFalse(vimeo.getIFrameSrc(style).isPresent());
		assertNull(vimeo.getIFrameWidth());
		assertNull(vimeo.getIFrameHeight());
		assertNull(vimeo.getIFrameAspectRatio());
		assertNull(vimeo.getVimeoLayout());
	}

	@Test
	void testDataLayerJson() {
		Utils.enableDataLayer(context, true);
		String expected = "{\"embed-6fac9b9214\":{\"@type\":\"core/wcm/components/embed/v2/embed\",\"embeddableProperties\":{\"vimeoVideoId\":\"9389973\"}}}";
		VimeoImpl vimeo = getVimeoUnderTest(PATH_VIDEO_1, context.currentPage(ROOT_PAGE));
		assertEquals(Json.createReader(new StringReader(expected)).read(),
				Json.createReader(new StringReader(vimeo.getData().getJson())).read());
	}

	private VimeoImpl getVimeoUnderTest(String resourcePath, Page page) {
		Resource resource = context.resourceResolver().getResource(resourcePath);
		if (resource == null) {
			throw new IllegalStateException("Did you forget to define test resource " + resourcePath + "?");
		}
		MockSlingHttpServletRequest request = new MockSlingHttpServletRequest(context.resourceResolver(),
				context.bundleContext());
		SlingBindings bindings = new SlingBindings();
		bindings.put(SlingBindings.RESOURCE, resource);
		bindings.put(SlingBindings.REQUEST, request);
		bindings.put(WCMBindingsConstants.NAME_CURRENT_PAGE, page);
		bindings.put(WCMBindingsConstants.NAME_COMPONENT_CONTEXT, componentContext);
		bindings.put(WCMBindingsConstants.NAME_PROPERTIES, resource.getValueMap());
		((MockRequestPathInfo) request.getRequestPathInfo()).setResourcePath(resourcePath);
		request.setResource(resource);
		request.setAttribute(SlingBindings.class.getName(), bindings);
		ModelFactory modelFactory = context.getService(ModelFactory.class);
		VimeoImpl vimeoImpl = (VimeoImpl) modelFactory.createModel(request, Vimeo.class);
		return vimeoImpl;
	}

	@Test
	void testGetStyleForWrappedResource() {
		VimeoImpl vimeo = getVimeoUnderTest(PATH_VIDEO_3, page);
		Resource resource = context.resourceResolver().getResource(PATH_VIDEO_3);
		if (resource == null) {
			throw new IllegalStateException("Did you forget to define test resource " + PATH_VIDEO_3 + "?");
		}
		context.contentPolicyMapping(resource.getResourceType(), Collections.singletonMap("test", "foo"));
		Style style = vimeo.getStyleForWrappedResource(new ResourceWrapper(resource) {
			@Override
			public String getResourceType() {
				return "overriddenResourceType";
			}
		});
		assertEquals("foo", style.get("test", "bar"));
	}
}

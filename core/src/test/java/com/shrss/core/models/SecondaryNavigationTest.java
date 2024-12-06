package com.shrss.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;


/**
 * The Class SecondaryNavigationTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class SecondaryNavigationTest {

	/** The aem context. */
	private final AemContext aemContext = new AemContext();

	/** The Constant SECONDARY_NAV_NO_LINKS. */
	private static final String SECONDARY_NAV_NO_LINKS = "/content/headerFive";

	/** The secondary navigation. */
	private SecondaryNavigation secondaryNavigation;

	/** The resource resolver. */
	private ResourceResolver resourceResolver;

	/** The page manager. */
	private PageManager pageManager;

	/** The page. */
	private Page page;

	/**
	 * Sets the up.
	 *
	 * @throws NoSuchFieldException the no such field exception
	 */
	@BeforeEach
	void setUp() throws NoSuchFieldException {
		resourceResolver = mock(ResourceResolver.class);
		pageManager = mock(PageManager.class);
		page = mock(Page.class);
		aemContext.addModelsForClasses(SecondaryNavigation.class);
		aemContext.load().json("/com/shrss/core/models/mainNavigation/secondaryNavigation.json",
				"/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		aemContext.load().json("/com/shrss/core/models/mainNavigation/primaryNavigationEmpty.json",
				SECONDARY_NAV_NO_LINKS);
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		secondaryNavigation = currentResource.adaptTo(SecondaryNavigation.class);
		PrivateAccessor.setField(secondaryNavigation, "resourceResolver", resourceResolver);
	}

	/**
	 * Test get tertiary nav.
	 */
	@Test
	void testGetTertiaryNav() {
		List<TertiaryNavigation> tertiaryNav = secondaryNavigation.getTertiaryNav();
		assertNotNull(tertiaryNav);
		assertEquals(2, tertiaryNav.size());
	}

	/**
	 * Test get show hide.
	 */
	@Test
	void testGetShowHide() {
		assertEquals("false", secondaryNavigation.getShowHide());
	}

	/**
	 * Test get url target.
	 */
	@Test
	void testGetUrlTarget() {
		assertEquals("_self", secondaryNavigation.getUrlTarget());
	}

	/**
	 * Test get url.
	 */
	@Test
	void testGetUrl() {
		when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		when(pageManager.getPage("/content/shrss/us/en/head1")).thenReturn(page);
		when(resourceResolver.map("/content/shrss/us/en/head1")).thenReturn("/content/shrss/us/en/head1");
		assertEquals("/content/shrss/us/en/head1.html", secondaryNavigation.getUrl());
	}
	
	/**
	 * Test root url.
	 */
	@Test
	void testRootUrl() {
		aemContext.load().json("/com/shrss/core/models/mainNavigation/secondaryNavigationRoot.json",
				"/content/shrss/us/en/head9/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/head9/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		secondaryNavigation = currentResource.adaptTo(SecondaryNavigation.class);
		assertEquals("/", secondaryNavigation.getUrl());
	}

	/**
	 * Test empty url.
	 */
	@Test
	void testEmptyUrl() {
		aemContext.load().json("/com/shrss/core/models/mainNavigation/secondaryNavigationUrlEmpty.json",
				"/content/shrss/us/en/head6/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/head6/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		secondaryNavigation = currentResource.adaptTo(SecondaryNavigation.class);
		assertEquals(StringUtils.EMPTY, secondaryNavigation.getUrl());
	}

	/**
	 * Test external url.
	 */
	@Test
	void testExternalUrl() {
		aemContext.load().json("/com/shrss/core/models/mainNavigation/secondaryNavigationExternal.json",
				"/content/shrss/us/en/head7/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/head7/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		secondaryNavigation = currentResource.adaptTo(SecondaryNavigation.class);
		assertEquals("https://www.google.com", secondaryNavigation.getUrl());
	}
	
	/**
	 * Test external http url.
	 */
	@Test
	void testExternalHttpUrl() {
		aemContext.load().json("/com/shrss/core/models/mainNavigation/secondaryNavigationOther.json",
				"/content/shrss/us/en/head8/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/head8/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0");
		secondaryNavigation = currentResource.adaptTo(SecondaryNavigation.class);
		assertEquals("http://www.google.com", secondaryNavigation.getUrl());
	}

	/**
	 * Test get label.
	 */
	@Test
	void testGetLabel() {
		assertEquals("North America", secondaryNavigation.getLabel());
	}

	/**
	 * Test T ertiary nav null.
	 */
	@Test
	void testTErtiaryNavNull() {
		SecondaryNavigation secondaryNavigation = aemContext.currentResource(SECONDARY_NAV_NO_LINKS)
				.adaptTo(SecondaryNavigation.class);
		assertEquals(Collections.EMPTY_LIST, secondaryNavigation.getTertiaryNav(), "It is empty.");
	}
}

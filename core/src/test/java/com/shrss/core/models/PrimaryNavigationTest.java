package com.shrss.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

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
 * The Class PrimaryNavigationTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class PrimaryNavigationTest {

	/** The aem context. */
	private final AemContext aemContext = new AemContext();

	/** The Constant PRIMARY_NAV_NO_LINKS. */
	private static final String PRIMARY_NAV_NO_LINKS = "/content/headerFive";

	/** The primary navigation. */
	private PrimaryNavigation primaryNavigation;

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
		aemContext.addModelsForClasses(PrimaryNavigation.class);
		aemContext.load().json("/com/shrss/core/models/mainNavigation/primaryNavigation.json",
				"/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation/primaryNav/item0");
		aemContext.load().json("/com/shrss/core/models/mainNavigation/primaryNavigationEmpty.json",
				PRIMARY_NAV_NO_LINKS);
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation/primaryNav/item0");
		primaryNavigation = currentResource.adaptTo(PrimaryNavigation.class);
		PrivateAccessor.setField(primaryNavigation, "resourceResolver", resourceResolver);
	}

	/**
	 * Test get label.
	 */
	@Test
	void testGetLabel() {
		assertEquals("Destinations", primaryNavigation.getLabel());
	}

	/**
	 * Test get url.
	 */
	@Test
	void testGetUrl() {
		when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		when(pageManager.getPage("/content/shrss/us/en/head1")).thenReturn(page);
		when(resourceResolver.map("/content/shrss/us/en/head1")).thenReturn("/content/shrss/us/en/head1");
		assertEquals("/content/shrss/us/en/head1.html", primaryNavigation.getUrl());
	}

	/**
	 * Test get url target.
	 */
	@Test
	void testGetUrlTarget() {
		assertEquals("_self", primaryNavigation.getUrlTarget());
	}

	/**
	 * Test get show hide.
	 */
	@Test
	void testGetShowHide() {
		assertEquals("false", primaryNavigation.getShowHide());
	}

	/**
	 * Test get secondary nav.
	 */
	@Test
	void testGetSecondaryNav() {
		List<SecondaryNavigation> secondaryNav = primaryNavigation.getSecondaryNav();
		assertEquals(1, secondaryNav.size());
		SecondaryNavigation secondaryNavigation = secondaryNav.get(0);
		assertEquals("North America", secondaryNavigation.getLabel());
	}

	/**
	 * Test secondary nav null.
	 */
	@Test
	void testSecondaryNavNull() {
		PrimaryNavigation primaryNavigation = aemContext.currentResource(PRIMARY_NAV_NO_LINKS)
				.adaptTo(PrimaryNavigation.class);
		assertEquals(Collections.EMPTY_LIST, primaryNavigation.getSecondaryNav(), "It is empty.");
	}
}

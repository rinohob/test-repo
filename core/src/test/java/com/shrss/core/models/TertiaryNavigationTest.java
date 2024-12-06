package com.shrss.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;


/**
 * The Class TertiaryNavigationTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class TertiaryNavigationTest {

	/** The aem context. */
	private final AemContext aemContext = new AemContext();

	/** The tertiary navigation. */
	private TertiaryNavigation tertiaryNavigation;

	/** The page manager. */
	private PageManager pageManager;

	/** The page. */
	private Page page;

	/** The resource resolver. */
	private ResourceResolver resourceResolver;

	/**
	 * Sets the up.
	 *
	 * @throws NoSuchFieldException the no such field exception
	 */
	@BeforeEach
	void setUp() throws NoSuchFieldException {
		aemContext.addModelsForClasses(TertiaryNavigation.class);
		aemContext.load().json("/com/shrss/core/models/mainNavigation/tertiaryNavigation.json",
				"/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0/tertiaryNav/item0");
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation/primaryNav/item0/secondaryNav/item0/tertiaryNav/item0");
		tertiaryNavigation = currentResource.adaptTo(TertiaryNavigation.class);
		resourceResolver = mock(ResourceResolver.class);
		pageManager = mock(PageManager.class);
		page = mock(Page.class);
		PrivateAccessor.setField(tertiaryNavigation, "resourceResolver", resourceResolver);
	}

	/**
	 * Test get tertiary label.
	 */
	@Test
	void testGetTertiaryLabel() {
		assertEquals("New York", tertiaryNavigation.getTertiaryLabel());
	}

	/**
	 * Test get tertiary url.
	 */
	@Test
	void testGetTertiaryUrl() {
		when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		when(pageManager.getPage("/content/shrss/us/en/head1")).thenReturn(page);
		when(resourceResolver.map("/content/shrss/us/en/head1")).thenReturn("/content/shrss/us/en/head1");
		assertEquals("/content/shrss/us/en/head1.html", tertiaryNavigation.getTertiaryUrl());
	}

	/**
	 * Test get tertiary target.
	 */
	@Test
	void testGetTertiaryTarget() {
		assertEquals("_self", tertiaryNavigation.getTertiaryTarget());
	}

	/**
	 * Test get tertiary show hide.
	 */
	@Test
	void testGetTertiaryShowHide() {
		assertEquals("false", tertiaryNavigation.getTertiaryShowHide());
	}
}

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
 * The Class MainNavigationLogoTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class MainNavigationLogoTest {

	/** The aem context. */
	private final AemContext aemContext = new AemContext();

	/** The main navigation logo. */
	private MainNavigationLogo mainNavigationLogo;

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
		resourceResolver = mock(ResourceResolver.class);
		pageManager = mock(PageManager.class);
		page = mock(Page.class);
		aemContext.addModelsForClasses(MainNavigationLogo.class);
		aemContext.load().json("/com/shrss/core/models/mainNavigation/mainNavigationLogo.json",
				"/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation/logo/item0");
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation/logo/item0");
		mainNavigationLogo = currentResource.adaptTo(MainNavigationLogo.class);
		PrivateAccessor.setField(mainNavigationLogo, "resourceResolver", resourceResolver);
	}

	/**
	 * Test get logo url.
	 */
	@Test
	void testGetLogoUrl() {
		when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
		when(pageManager.getPage("/content/shrss/us/en/head1")).thenReturn(page);
		when(resourceResolver.map("/content/shrss/us/en/head1")).thenReturn("/content/shrss/us/en/head1");
		assertEquals("/content/shrss/us/en/head1.html", mainNavigationLogo.getLogoUrl());
	}

	/**
	 * Test get logo target.
	 */
	@Test
	void testGetLogoTarget() {
		assertEquals("_self", mainNavigationLogo.getLogoTarget());
	}

	/**
	 * Test get logo text.
	 */
	@Test
	void testGetLogoText() {
		assertEquals("Hard Rock", mainNavigationLogo.getLogoText());
	}

	/**
	 * Test get file reference.
	 */
	@Test
	void testGetFileReference() {
		assertEquals("/content/dam/shrss/asset.jpg", mainNavigationLogo.getFileReference());
	}
}

package com.shrss.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;


/**
 * The Class SubNavItemsModelTest.
 */
@ExtendWith(AemContextExtension.class)
class SubNavItemsModelTest {

	/** The context. */
	private final AemContext context = new AemContext();

	/** The sub nav items model. */
	private SubNavItemsModel subNavItemsModel;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		// Create a mock resource with the properties
		context.create().resource("/content/subnavitem", "title", "Sample Title", "pagePath", "/content/sample-page",
				"ctaTarget", "_blank");

		// Adapt the resource to the model
		Resource resource = context.resourceResolver().getResource("/content/subnavitem");
		subNavItemsModel = resource.adaptTo(SubNavItemsModel.class);
	}

	/**
	 * Test get title.
	 */
	@Test
	void testGetTitle() {
		assertEquals("Sample Title", subNavItemsModel.getTitle());
	}

	/**
	 * Test get page path.
	 */
	@Test
	void testGetPagePath() {
		assertEquals("/content/sample-page", subNavItemsModel.getPagePath());
	}

	/**
	 * Test get cta target.
	 */
	@Test
	void testGetCtaTarget() {
		assertEquals("_blank", subNavItemsModel.getCtaTarget());
	}

	/**
	 * Test is active.
	 */
	@Test
	void testIsActive() {
		assertFalse(subNavItemsModel.isActive());

		subNavItemsModel.setActive(true);
		assertTrue(subNavItemsModel.isActive());
	}

	/**
	 * Test set page path.
	 */
	@Test
	void testSetPagePath() {
		subNavItemsModel.setPagePath("/content/new-page");
		assertEquals("/content/new-page", subNavItemsModel.getPagePath());
	}
}

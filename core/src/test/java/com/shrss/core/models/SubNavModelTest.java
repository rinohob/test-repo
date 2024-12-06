package com.shrss.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;


/**
 * The Class SubNavModelTest.
 */
@ExtendWith(AemContextExtension.class)
class SubNavModelTest {

	/** The context. */
	private final AemContext context = new AemContext();
	
	/** The resource. */
	private Resource resource;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		// Create the resource and set the properties
		resource = context.create().resource("/content/subnav", "mobileSubnav", "true", "accessibilityLabel", "Label");

		// Create the subNavItems child resource
		Resource subNavItemsResource = context.create().resource(resource, "subNavItems");

		// Add subNavItems to the context
		context.create().resource(subNavItemsResource, "item1", "title", "Item 1", "path", "/content/item1");
		context.create().resource(subNavItemsResource, "item2", "title", "Item 2", "path", "/content/item2");
	}

	/**
	 * Test sub nav model.
	 */
	@Test
	void testSubNavModel() {
		// Adapt the resource to the model
		SubNavModel subNavModel = resource.adaptTo(SubNavModel.class);

		// Verify that the subNavItems list is not null and has the expected size
		List<SubNavItemsModel> subNavItems = subNavModel.getSubNavItems();
		assertNotNull(subNavItems);
		assertEquals(2, subNavItems.size());

		// Verify the mobileSubnav and label properties
		assertEquals("true", subNavModel.getMobileSubnav());
		assertEquals("Label", subNavModel.getAccessLabel());
	}
}

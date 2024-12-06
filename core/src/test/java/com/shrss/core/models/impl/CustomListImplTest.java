package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.shrss.core.models.CustomList;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class CustomListImplTest {

	private final AemContext context = new AemContext(ResourceResolverType.JCR_MOCK);

	@BeforeEach
	public void setUp() {
		final String expectedColumns = "column1,column2,column3";
		context.create().resource("/content/test", "columns", expectedColumns);
	}

	/**
	 * Test get columns.
	 */
	@Test
	void testGetColumns() {
		Resource resource = context.resourceResolver().getResource("/content/test");
		CustomList customList = resource.adaptTo(CustomList.class);
		assertNotNull(customList);
		assertEquals("column1,column2,column3", customList.getColumns());
	}
}

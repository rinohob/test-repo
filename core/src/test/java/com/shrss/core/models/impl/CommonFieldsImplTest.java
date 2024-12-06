package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;


/**
 * The Class CommonFieldsImplTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class CommonFieldsImplTest {

	/** The aem context. */
	private final AemContext aemContext = new AemContext();

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		aemContext.addModelsForClasses(CommonFieldsImpl.class);
		aemContext.load().json("/com/shrss/core/models/commonFields/commonFields.json",
				"/content/experience-fragments/shrss/us/en/site/testonee/master/jcr:content/root/experiencefragment");
		Resource currentResource = aemContext.currentResource(
				"/content/experience-fragments/shrss/us/en/site/testonee/master/jcr:content/root/experiencefragment");
		CommonFieldsImpl model = currentResource.adaptTo(CommonFieldsImpl.class);
		assertNotNull(model);
	}

	/**
	 * Test get id.
	 */
	@Test
	void testGetId() {
		Resource currentResource = aemContext.currentResource(
				"/content/experience-fragments/shrss/us/en/site/testonee/master/jcr:content/root/experiencefragment");
		CommonFieldsImpl model = currentResource.adaptTo(CommonFieldsImpl.class);
		assertEquals("id", model.getId());
	}

	/**
	 * Test get override class.
	 */
	@Test
	void testGetOverrideClass() {
		Resource currentResource = aemContext.currentResource(
				"/content/experience-fragments/shrss/us/en/site/testonee/master/jcr:content/root/experiencefragment");
		CommonFieldsImpl model = currentResource.adaptTo(CommonFieldsImpl.class);
		assertEquals("testoneeclass", model.getOverrideClass());
	}

}

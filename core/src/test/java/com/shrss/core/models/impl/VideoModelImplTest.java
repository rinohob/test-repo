package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.shrss.core.models.VideoModel;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * The Class VideoModelImplTest.
 */
@ExtendWith(AemContextExtension.class)
class VideoModelImplTest {

	/** The context. */
	private final AemContext context = new AemContext();

	/** The resource. */
	private Resource resource;

	/**
	 * Set up.
	 */
	@BeforeEach
	void setUp() {
		// Create the resource and set the properties
		resource = context.create().resource("/content/video", "externalUrl", "https://www.google.com", "type",
				"externalUrl", "externalWidth", "100");
	}

	/**
	 * Test video model.
	 */
	@Test
	void testVideoModel() {
		// Adapt the resource to the model
		VideoModel videoModel = resource.adaptTo(VideoModel.class);
		assertEquals("https://www.google.com", videoModel.getExternalUrl());
		assertEquals("100", videoModel.getExternalWidth());
		assertEquals("externalUrl", videoModel.getType());
	}
}

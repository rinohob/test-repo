package com.shrss.core.models.impl;

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
 * The Class SocialMediaImplTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class SocialMediaImplTest {

	/** The aemContext. */
	private final AemContext aemContext = new AemContext();

	/**
	 * Setup the context
	 */
	@BeforeEach
	void setUp() {
		aemContext.addModelsForClasses(SocialMediaImpl.class);
		aemContext.load().json("/com/shrss/core/models/socialMedia/socialMedia.json",
				"/content/shrss/us/en/herocarousel/social/jcr:content/root/container/container/socialmedia");
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/herocarousel/social/jcr:content/root/container/container/socialmedia");
		currentResource.adaptTo(SocialMediaImpl.class);
	}

	/**
	 * Get the social items
	 */
	@Test
	void getSocialItems() {
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/herocarousel/social/jcr:content/root/container/container/socialmedia");
		SocialMediaImpl socialMedia = currentResource.adaptTo(SocialMediaImpl.class);
		assertNotNull(socialMedia.getSocialMediaLinks());
	}

	/**
	 * Get the social cta items
	 */
	@Test
	void getSocialCtaItems() {
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/herocarousel/social/jcr:content/root/container/container/socialmedia");
		SocialMediaImpl socialMedia = currentResource.adaptTo(SocialMediaImpl.class);
		assertNotNull(socialMedia.getSocialMediaLinks().get(0).getCtaTarget());
	}

	/**
	 * Get the social media items
	 */
	@Test
	void getSocialMediaItems() {
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/herocarousel/social/jcr:content/root/container/container/socialmedia");
		SocialMediaImpl socialMedia = currentResource.adaptTo(SocialMediaImpl.class);
		assertNotNull(socialMedia.getSocialMediaLinks().get(0).getSocialMedia());
	}

	/**
	 * Get the social media paths
	 */
	@Test
	void getSocialMediaPaths() {
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/herocarousel/social/jcr:content/root/container/container/socialmedia");
		SocialMediaImpl socialMedia = currentResource.adaptTo(SocialMediaImpl.class);
		assertNotNull(socialMedia.getSocialMediaLinks().get(0).getPagePath());
	}
	
	/**
	 * Get the social media paths
	 */
	@Test
	void getSocialMediaLabel() {
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/herocarousel/social/jcr:content/root/container/container/socialmedia");
		SocialMediaImpl socialMedia = currentResource.adaptTo(SocialMediaImpl.class);
		assertNotNull(socialMedia.getSocialMediaLinks().get(0).getLabel());
	}

}

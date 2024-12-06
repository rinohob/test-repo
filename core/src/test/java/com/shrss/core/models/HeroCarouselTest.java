package com.shrss.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * The Class HeroCarouselTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class HeroCarouselTest {

	/** The context. */
	private final AemContext aemContext = new AemContext();

	private static final String NO_START_INDEX = "/content/headerOne";

	/**
	 * Setup the context
	 */
	@BeforeEach
	void setUp() {
		aemContext.addModelsForClasses(HeroCarousel.class);
		aemContext.load().json("/com/shrss/core/models/heroCarousel/heroCarousel.json",
				"/content/shrss/us/en/herocarousel/herocarouselone/jcr:content/root/container/container/carousel");
		aemContext.load().json("/com/shrss/core/models/heroCarousel/heroCarouselEmpty.json", NO_START_INDEX);
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/herocarousel/herocarouselone/jcr:content/root/container/container/carousel");
		currentResource.adaptTo(HeroCarousel.class);
	}

	/**
	 * Get the start index
	 */
	@Test
	void getStartIndex() {
		Resource currentResource = aemContext.currentResource(
				"/content/shrss/us/en/herocarousel/herocarouselone/jcr:content/root/container/container/carousel");
		HeroCarousel heroCarousel = currentResource.adaptTo(HeroCarousel.class);
		assertEquals("1", heroCarousel.getStartIndex());
	}

	/**
	 * Get the empty index
	 */
	@Test
	void getEmptyIndex() {
		HeroCarousel heroCarousel = aemContext.currentResource(NO_START_INDEX).adaptTo(HeroCarousel.class);
		assertEquals("0", heroCarousel.getStartIndex());
	}

}

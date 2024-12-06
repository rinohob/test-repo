package com.shrss.core.models.impl;

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
 * The Class TitleImplTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class TitleImplTest {

    /** The aem context. */
    private final AemContext aemContext = new AemContext();

    /** The title. */
    private TitleImpl title;

    /**
     * Sets the up.
     */
    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(TitleImpl.class);
        aemContext.load().json("/com/shrss/core/models/title.json", "/content/shrss/us/en/home/jcr:content/root/container/container/title");
        Resource currentResource = aemContext.currentResource("/content/shrss/us/en/home/jcr:content/root/container/container/title");
        title = currentResource.adaptTo(TitleImpl.class);
    }

    /**
     * Test get eyebrow.
     */
    @Test
    void testGetEyebrow() {
        assertEquals("Sample eyebrow", title.getEyebrow(), "Eyebrow value does not match");
    }

    /**
     * Test get eyebrow check.
     */
    @Test
    void testGetEyebrowCheck() {
        assertEquals("true", title.getEyebrowCheck(), "EyebrowCheck value does not match");
    }
}

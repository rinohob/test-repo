package com.shrss.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;
import java.nio.file.Paths;

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
 * The Class CrownNavigationItemTest.
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
class CrownNavigationItemTest {

    /** The aem context. */
    private final AemContext aemContext = new AemContext();

    /** The crown navigation item. */
    private CrownNavigationItem crownNavigationItem;

    /**
     * Sets the up.
     */
    @BeforeEach
    void setUp() {
    	aemContext.addModelsForClasses(CrownNavigationItem.class);
		Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        aemContext.load().json(absolutePath+"/com/shrss/core/models/crownNavigationItem.json",
                "/apps/shrss/components/crownnav");
        Resource currentResource = aemContext.currentResource("/apps/shrss/components/crownnav");
        crownNavigationItem = currentResource.adaptTo(CrownNavigationItem.class);
    }

    /**
     * Test label.
     */
    @Test
    void testLabel() {
        assertNotNull(crownNavigationItem);
        assertEquals("Casino", crownNavigationItem.getLabel());
    }

    /**
     * Test nav url.
     */
    @Test
    void testNavUrl() {
        assertNotNull(crownNavigationItem);
        assertEquals("https://casino.hardrock.com/", crownNavigationItem.getNavUrl());
    }

    /**
     * Test new tab.
     */
    @Test
    void testNewTab() {
        assertNotNull(crownNavigationItem);
        assertEquals("true", crownNavigationItem.getNewTab());
    }

    /**
     * Test show hide link.
     */
    @Test
    void testShowHideLink() {
        assertNotNull(crownNavigationItem);
        assertEquals("hide", crownNavigationItem.getShowHideLink());
    }
}

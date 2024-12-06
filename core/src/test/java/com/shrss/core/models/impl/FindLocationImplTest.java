package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.shrss.core.models.FindLocation;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FindLocationImplTest {

    private final AemContext aemContext = new AemContext();
    FindLocation model;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(FindLocationImpl.class);
        aemContext.load().json("/com/shrss/core/models/findLocation/findlocation.json", "/content/shrss/us/en/findlocation/jcr:content/root/container/container/findlocation");
        Resource currentResource = aemContext.currentResource("/content/shrss/us/en/findlocation/jcr:content/root/container/container/findlocation");
        FindLocation findLocation = currentResource.adaptTo(FindLocationImpl.class);
        assertNotNull(findLocation);
    }

    @Test
    void testGetLocationLabel() {
    	Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/findlocation/jcr:content/root/container/container/findlocation");
    	FindLocation findLocation = currentResource.adaptTo(FindLocation.class);
        assertEquals("Find a Hard Rock:", findLocation.getLocationLabel());
    }

    @Test
    void testGetPlaceholderText() {
    	Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/findlocation/jcr:content/root/container/container/findlocation");
    	FindLocation findLocation = currentResource.adaptTo(FindLocation.class);
        assertEquals("City,State or Zip/Postal Code", findLocation.getPlaceholderText());
    }

    @Test
    void testGetSearchText() {
    	Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/findlocation/jcr:content/root/container/container/findlocation");
    	FindLocation findLocation = currentResource.adaptTo(FindLocation.class);
        assertEquals("Search", findLocation.getSearchText());
    }

    @Test
    void testGetViewAllText() {
    	Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/findlocation/jcr:content/root/container/container/findlocation");
    	FindLocation findLocation = currentResource.adaptTo(FindLocation.class);
        assertEquals("View All", findLocation.getViewAllText());
    }

    @Test
    void testGetEmptyText() {
    	Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/findlocation/jcr:content/root/container/container/findlocation");
    	FindLocation findLocation = currentResource.adaptTo(FindLocation.class);
        assertEquals("Please provide a city, state, or zip/postal code", findLocation.getEmptyText());
    }

}
package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.shrss.core.models.CrownNavModel;
import com.shrss.core.models.CrownNavigationItem;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;


/**
 * JUnit test verifying the CrownNavModelTest
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class CrownNavModelImplTest {
	
	/** The aem context. */

	private final AemContext aemContext = new AemContext(ResourceResolverType.JCR_MOCK);
	
	/** The crown nav model. */
	private CrownNavModel crownNavModel;
	
	@Mock
	private ResourceResolver resourceResolver;

	/**
	 * Setup.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	public void setup() throws Exception {
		aemContext.addModelsForClasses(CrownNavModelImpl.class);
		Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        aemContext.load().json(absolutePath+"/com/shrss/core/models/crownnavigation.json",
                "/apps/shrss/components/crownnav");
        Resource currentResource = aemContext.currentResource("/apps/shrss/components/crownnav");
		crownNavModel = currentResource.adaptTo(CrownNavModelImpl.class);
	}

	/**
	 * Test loB links.
	 */
	@Test
	void testLoBLinks() {
		assertNotNull(crownNavModel, "Model should not be null");
		List<CrownNavigationItem> lobLinks = crownNavModel.getLobLinks();
		assertNotNull(lobLinks, "LobLinks should not be null");
		assertEquals(2, lobLinks.size(), "LobLinks size should be 2");

		CrownNavigationItem firstItem = lobLinks.get(0);
		assertEquals("Casino", firstItem.getLabel(), "First item label should be Casino");
		assertEquals("https://casino.hardrock.com/", firstItem.getNavUrl(), "First item URL should match");
		assertEquals("hide", firstItem.getShowHideLink(), "First item showHideLink should be hide");

		CrownNavigationItem secondItem = lobLinks.get(1);
		assertEquals("Cafes", secondItem.getLabel(), "Second item label should be Cafes");
		assertEquals("https://cafe.hardrock.com/", secondItem.getNavUrl(), "Second item URL should match");
		assertEquals("show", secondItem.getShowHideLink(), "Second item showHideLink should be show");
	}
	
	@Test
	void testInit() {
		List<CrownNavigationItem> lobLinks = crownNavModel.getLobLinks();
		
		 Iterator<CrownNavigationItem> mockIterator = mock(Iterator.class);
	        when(mockIterator.hasNext()).thenReturn(true, true, false);
	        when(mockIterator.next()).thenReturn(lobLinks.get(0)).thenReturn(lobLinks.get(1));
		
	        while (mockIterator.hasNext()) {
	        	CrownNavigationItem crownNavItem = mockIterator.next();
	        	crownNavItem.setActive(true);
	    		crownNavItem.setNavUrl("http://www/google.com");
	        }
		CrownNavigationItem crownNavItem = lobLinks.get(0);
		assertEquals(crownNavItem.isActive(), true);
		assertEquals(crownNavItem.getNavUrl(), "http://www/google.com");

	}
}
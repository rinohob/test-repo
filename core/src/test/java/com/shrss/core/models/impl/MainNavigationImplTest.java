package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.shrss.core.models.CrownCTAItem;
import com.shrss.core.models.CrownNavigationItem;
import com.shrss.core.models.MainNavigation;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class MainNavigationImplTest {

	private final AemContext aemContext = new AemContext();

	private static final String PRIMARY_NAV_NO_LINKS = "/content/headerFive";

	private static final String LOGO_NO_LINKS = "/content/headerOne";
	
	private static final String CROWN_CTA = "/right/crowncta";

	private static final String CROWN_NAV = "/left/lobLinks";

	@Mock
    private ResourceResolver resourceResolver;

    @Mock
    private SlingHttpServletRequest req;

    @Mock
    private Resource reqResource;

    @Mock
    private Resource parentResource;

    @Mock
    private Resource crownCTAResource;

    @Mock
    private Iterator<Resource> resourceIterator;

    @InjectMocks
    private MainNavigationImpl mainNavigation;
    
	@Mock
	private Resource crownNavigationResource;

	@BeforeEach
	void setUp() {
		aemContext.addModelsForClasses(MainNavigationImpl.class);
		aemContext.load().json("/com/shrss/core/models/mainNavigation/mainNavigation.json",
				"/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation");
		aemContext.load().json("/com/shrss/core/models/mainNavigation/primaryNavigationEmpty.json",
				PRIMARY_NAV_NO_LINKS);
		aemContext.load().json("/com/shrss/core/models/mainNavigation/primaryNavigationEmpty.json", LOGO_NO_LINKS);
		Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation");

		MainNavigation mainNavigation = currentResource.adaptTo(MainNavigationImpl.class);
		assertNotNull(mainNavigation);

		when(req.getResource()).thenReturn(reqResource);
		when(reqResource.getParent()).thenReturn(parentResource);
		when(parentResource.getPath()).thenReturn("/parent/path");

		when(resourceResolver.getResource("/parent/path" + "/right/crowncta")).thenReturn(crownCTAResource);
		when(crownCTAResource.listChildren()).thenReturn(resourceIterator);

		when(resourceResolver.getResource("/parent/path" + CROWN_NAV)).thenReturn(crownNavigationResource);
		when(crownNavigationResource.listChildren()).thenReturn(resourceIterator);
	}

	@Test
	void getMobileText() {
		Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation");
		MainNavigation mainNavigation = currentResource.adaptTo(MainNavigation.class);
		assertNotNull(mainNavigation.getMobileText());
		assertEquals("Mobile text", mainNavigation.getMobileText());
	}

	@Test
	void testGetPrimaryNav() {
		Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation");
		MainNavigation mainNavigation = currentResource.adaptTo(MainNavigation.class);

		assertNotNull(mainNavigation.getPrimaryNav());
		assertEquals(1, mainNavigation.getPrimaryNav().size());
	}

	@Test
	void testGetLogo() {
		Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/head3/jcr:content/root/container/container/mainnavigation");
		MainNavigation mainNavigation = currentResource.adaptTo(MainNavigation.class);

		assertNotNull(mainNavigation.getLogo());
		assertEquals(1, mainNavigation.getLogo().size());
	}

	@Test
	void testPrimaryNavNull() {
		MainNavigation mainNavigation = aemContext.currentResource(PRIMARY_NAV_NO_LINKS).adaptTo(MainNavigation.class);
		assertEquals(Collections.EMPTY_LIST, mainNavigation.getPrimaryNav(), "It is empty.");
	}

	@Test
	void testLogoNull() {
		MainNavigation mainNavigation = aemContext.currentResource(LOGO_NO_LINKS).adaptTo(MainNavigation.class);
		assertEquals(Collections.EMPTY_LIST, mainNavigation.getLogo(), "It is empty.");
	}

	@Test
	void testGetCtaItemList() {
        List<CrownCTAItem> expectedList = new ArrayList<>();
        Resource resource = mock(Resource.class);
        CrownCTAItem crownCTAItem = mock(CrownCTAItem.class);

        when(resourceIterator.hasNext()).thenReturn(true, false);
        when(resourceIterator.next()).thenReturn(resource);
        when(resource.adaptTo(CrownCTAItem.class)).thenReturn(crownCTAItem);

        expectedList.add(crownCTAItem);

        List<CrownCTAItem> actualList = mainNavigation.getCtaItemList();

        assertEquals(expectedList, actualList);

	}

	@Test
	void testGetLobLinks() {
		List<CrownNavigationItem> expectedList = new ArrayList<>();
		Resource resource = mock(Resource.class);
		CrownNavigationItem crownNavigationItem = mock(CrownNavigationItem.class);

		when(resourceIterator.hasNext()).thenReturn(true, false);
		when(resourceIterator.next()).thenReturn(resource);
		when(resource.adaptTo(CrownNavigationItem.class)).thenReturn(crownNavigationItem);

		expectedList.add(crownNavigationItem);

		List<CrownNavigationItem> actualList = mainNavigation.getLobLinks();

		assertEquals(expectedList, actualList);
	}

	@Test
	public void testGetCtaItemList_NoResources() {
		when(resourceResolver.getResource("/parent/path" + CROWN_CTA)).thenReturn(null);

		List<CrownCTAItem> actualList = mainNavigation.getCtaItemList();

		assertTrue(actualList.isEmpty());
	}

}
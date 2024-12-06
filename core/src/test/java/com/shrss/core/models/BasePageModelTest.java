package com.shrss.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.Page;


/**
 * The Class BasePageModelTest.
 */
@ExtendWith(MockitoExtension.class)
class BasePageModelTest {

	/** The Constant TEST_PAGE_PATH. */
	private static final String TEST_PAGE_PATH = "/content/test-page";
	
	/** The Constant TEST_PUBLISH_URL. */
	private static final String TEST_PUBLISH_URL = "http://localhost:4503/content/test-page.html";
	
	/** The Constant TEST_SUBCLIENTLIB. */
	private static final String TEST_SUBCLIENTLIB = "custom-sub-clientlib";
	
	/** The Constant TEST_MAINCLIENTLIB. */
	private static final String TEST_MAINCLIENTLIB = "custom-main-clientlib";

	/** The base page model. */
	@InjectMocks
	private BasePageModel basePageModel;

	/** The externalizer. */
	@Mock
	private Externalizer externalizer;

	/** The resource resolver. */
	@Mock
	private ResourceResolver resourceResolver;

	/** The resource. */
	@Mock
	private Resource resource;

	/** The current page. */
	@Mock
	private Page currentPage;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	public void setUp() {
		lenient().when(resourceResolver.adaptTo(Externalizer.class)).thenReturn(externalizer);
		lenient().when(currentPage.getPath()).thenReturn(TEST_PAGE_PATH);
	}

	/**
	 * Test get page url.
	 */
	@Test
	void testGetPageUrl() {
		lenient().when(externalizer.publishLink(resourceResolver, TEST_PAGE_PATH))
				.thenReturn("http://localhost:4503/content/test-page");
		String pageUrl = basePageModel.getPageUrl();
		assertEquals(TEST_PUBLISH_URL, pageUrl);
	}

	/**
	 * Test get sub client lib with value.
	 */
	@Test
	void testGetSubClientLibWithValue() {
		basePageModel.subClientLib = TEST_SUBCLIENTLIB;
		assertEquals(TEST_SUBCLIENTLIB, basePageModel.getSubClientLib());
	}

	/**
	 * Test get sub client lib with default.
	 */
	@Test
	void testGetSubClientLibWithDefault() {
		basePageModel.subClientLib = "";
		assertEquals(BasePageModel.DEFAULT_SUBCLIENTLIB, basePageModel.getSubClientLib());
	}

	/**
	 * Test get main client lib with value.
	 */
	@Test
	void testGetMainClientLibWithValue() {
		basePageModel.mainClientLib = TEST_MAINCLIENTLIB;
		assertEquals(TEST_MAINCLIENTLIB, basePageModel.getMainClientLib());
	}

	/**
	 * Test get main client lib with default.
	 */
	@Test
	void testGetMainClientLibWithDefault() {
		basePageModel.mainClientLib = "";
		assertEquals(BasePageModel.DEFAULT_MAINCLIENTLIB, basePageModel.getMainClientLib());
	}
}

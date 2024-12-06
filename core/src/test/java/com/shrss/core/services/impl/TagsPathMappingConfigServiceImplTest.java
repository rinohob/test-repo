package com.shrss.core.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TagsPathMappingConfigServiceImplTest {

	@InjectMocks
	private TagsPathMappingConfigServiceImpl tagsPathMappingConfigService;

	@Mock
	private ResourceResolverFactory resolverFactory;

	Resource mockResource;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockResource = mock(Resource.class);

		ValueMap mockValueMap1 = mock(ValueMap.class);
		ValueMap mockValueMap2 = mock(ValueMap.class);

		Resource child1 = mock(Resource.class);
		when(mockValueMap1.get("jcr:title", String.class)).thenReturn("path1");
		when(mockValueMap1.get("value", String.class)).thenReturn("tag1");
		when(child1.getValueMap()).thenReturn(mockValueMap1);

		Resource child2 = mock(Resource.class);
		when(mockValueMap2.get("jcr:title", String.class)).thenReturn("path2");
		when(mockValueMap2.get("value", String.class)).thenReturn("tag2");
		when(child2.getValueMap()).thenReturn(mockValueMap2);

		when(mockResource.getChildren()).thenReturn(Arrays.asList(child1, child2));
	}

	@Test
	void testGetTagsPathMapping() throws LoginException {
		ResourceResolver mockResourceResolver = mock(ResourceResolver.class);
		when(mockResourceResolver.getResource(eq("/etc/acs-commons/lists/path-tags-mapping/jcr:content/list")))
				.thenReturn(mockResource);

		tagsPathMappingConfigService.resolverFactory = resolverFactory;
		when(resolverFactory.getServiceResourceResolver(anyMap())).thenReturn(mockResourceResolver);

		Map<String, String> result = tagsPathMappingConfigService.getTagsPathMapping();

		assertEquals("tag1", result.get("path1"));

	}

	@Test
	void testGetAllTagsByPath() {
		tagsPathMappingConfigService.map.put("path1", "tag1");
		tagsPathMappingConfigService.map.put("path2", "tag2");
		List<String> tags = tagsPathMappingConfigService.getAllTagsByPath("path1");
		assertEquals(1, tags.size());
		assertEquals("tag1", tags.get(0));

	}
}

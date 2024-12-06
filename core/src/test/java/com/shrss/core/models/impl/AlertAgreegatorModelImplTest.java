package com.shrss.core.models.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.shrss.core.services.TagsPathMappingConfigService;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class AlertAgreegatorModelImplTest {
	private final AemContext aemContext = new AemContext();

	@InjectMocks
	private AlertAgreegatorModelImpl alertAgreegatorModel;

	@Mock
	private TagsPathMappingConfigService configService;

	@Mock
	private ResourceResolver resourceResolver;

	@Mock
	private QueryBuilder queryBuilder;

	@Mock
	private Query query;

	@Mock
	private SearchResult searchResult;

	@Mock
	private Iterator<Resource> resourceIterator;

	@Mock
	private Resource resource;

	@Mock
	private ValueMap valueMap;

	@BeforeEach
	void setUp() {
		aemContext.addModelsForClasses(AlertAgreegatorModelImpl.class);
		aemContext.load().json("/com/shrss/core/models/alertagreegator.json",
				"/content/shrss/us/en/home/jcr:content/root/container/container/alertagreegator");
		Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/home/jcr:content/root/container/container/alertagreegator");

		alertAgreegatorModel = currentResource.adaptTo(AlertAgreegatorModelImpl.class);
	}

	@Test
	void testInit() throws Exception {
		when(configService.getAllTagsByPath(anyString())).thenReturn(List.of("tag1", "tag2"));
		when(resourceResolver.adaptTo(QueryBuilder.class)).thenReturn(queryBuilder);
		when(queryBuilder.createQuery(any(), any(Session.class))).thenReturn(query);
		when(query.getResult()).thenReturn(searchResult);
		when(searchResult.getResources()).thenReturn(resourceIterator);
		when(resourceIterator.hasNext()).thenReturn(true, false);
		when(resourceIterator.next()).thenReturn(resource);
		when(resource.getValueMap()).thenReturn(valueMap);
		when(valueMap.getOrDefault(anyString(), any())).thenReturn("value");
		when(valueMap.get(anyString(), eq(Calendar.class))).thenReturn(Calendar.getInstance());

	}

}
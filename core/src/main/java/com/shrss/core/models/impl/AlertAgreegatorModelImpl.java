package com.shrss.core.models.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.google.gson.JsonObject;
import com.shrss.core.models.AlertAgreegatorModel;
import com.shrss.core.services.TagsPathMappingConfigService;

import lombok.Getter;

@Model(adaptables = { Resource.class, SlingHttpServletRequest.class }, adapters = {
		AlertAgreegatorModel.class },
resourceType = { AlertAgreegatorModelImpl.RESOURCE_TYPE },
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class AlertAgreegatorModelImpl implements AlertAgreegatorModel {

	protected static final String RESOURCE_TYPE = "shrss/components/alertagreegator";
	
	private static final Logger logger = LoggerFactory.getLogger(AlertAgreegatorModelImpl.class);

	@ValueMapValue
	@Getter
	private String experienceSource;

	@ScriptVariable
	private Page currentPage;

	private static final String GLOBAL_TAG = "shrss:global";

	private static final String ALERT_RESOURCE_TYPE = "shrss/components/alert";

	private static final String TYPE = "nt:unstructured";

	private static final String TAG_PROPERTY = "cq:tags";

	private static final String TRUE = "true";
	
	private static final String FROM_DATE = "fromDate";
	
	private static final String TO_DATE = "toDate";
	
	private static final String ALERT_CONTENT = "alertContent";
	
	private static final String ARIA_LABEL = "ariaLabel";
	
	private static final String START_DATE = "startDate";
	
	private static final String END_DATE = "endDate";
	
	private static final String SHOW_ICON = "showIcon";
	
	private static final String MOBILE_CONTENT = "alertMobileContent";
	
	private static final String DISMISS_ALERT = "dismissAlert";
	
	private static final String CLASS_NAME = "className";
	
	private static final String TEXT = "text";
	
	private static final String SHOW_HIDE_ICON = "showHideIcon";
	
	private static final String MOBILE_TEXT = "mobileText";
	
	private static final String CLASS = "class";
	
	private static final String ID = "id";
	
	@Inject
	TagsPathMappingConfigService configService;

	@SlingObject
	private ResourceResolver resourceResolver;

	@Getter
	public String jsonArray;

	@SlingObject
	private Resource resource;

	@Getter
	private String resourcePath;

	@PostConstruct
	protected void init() {
		if (null == experienceSource) {
			logger.info("Experience Fragment not selected for Alert Agreegator");
			return;
		}
		resourcePath = resource.getPath();
		List<String> tags = configService.getAllTagsByPath(currentPage.getPath());
		if (tags.isEmpty()) {
			logger.info("No tags found for path {}", currentPage.getPath());
			return;
		}
		QueryBuilder queryBuilder = resourceResolver.adaptTo(QueryBuilder.class);

		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("type", TYPE);
		queryParams.put("path", experienceSource);
		queryParams.put("1_property", TAG_PROPERTY);
		queryParams.put("1_p.or", TRUE);
		queryParams.put("sling:resourceType", ALERT_RESOURCE_TYPE);
		short i = 1;
		for (String tag : tags) {
			queryParams.put("1_property." + i + "_value", tag);
			i++;
		}
		queryParams.put("1_property." + i + "_value", GLOBAL_TAG);

		try {
			Query query = queryBuilder.createQuery(PredicateGroup.create(queryParams),
					resourceResolver.adaptTo(Session.class));
			SearchResult result = query.getResult();
			Iterator<Resource> resources = result.getResources();
			List<JsonObject> jsonArrayList = new ArrayList<>();

			while (resources.hasNext()) {
				Resource resource = resources.next();
				jsonArrayList.add(resourceToJson(resource));
			}
			jsonArray = jsonArrayList.toString();
		} catch (UnsupportedOperationException  e) {
			logger.error("Error in Alert Agreegator {}", e.getMessage());
		}
		catch (Exception  e) {
			logger.error("Error in Alert Agreegator {}", e.getMessage());
		}
	}

	private JsonObject resourceToJson(Resource resource) {
		JsonObject jsonObj = new JsonObject();
		ValueMap valueMap = resource.getValueMap();
		jsonObj.addProperty(ID, valueMap.getOrDefault(ID, "1").toString());
		jsonObj.addProperty(ALERT_CONTENT, valueMap.getOrDefault(TEXT, StringUtils.EMPTY).toString());
		jsonObj.addProperty(START_DATE, valueMap.getOrDefault(FROM_DATE, StringUtils.EMPTY).toString());
		jsonObj.addProperty(END_DATE, valueMap.getOrDefault(TO_DATE, StringUtils.EMPTY).toString());
		jsonObj.addProperty(SHOW_ICON, valueMap.getOrDefault(SHOW_HIDE_ICON, "false").toString());
		jsonObj.addProperty(MOBILE_CONTENT, valueMap.getOrDefault(MOBILE_TEXT, StringUtils.EMPTY).toString());
		jsonObj.addProperty(DISMISS_ALERT, valueMap.getOrDefault(DISMISS_ALERT, "false").toString());
		jsonObj.addProperty(CLASS_NAME, valueMap.getOrDefault(CLASS, StringUtils.EMPTY).toString());
		jsonObj.addProperty(ARIA_LABEL, valueMap.getOrDefault(ARIA_LABEL, StringUtils.EMPTY).toString());
		return jsonObj;
	}

}

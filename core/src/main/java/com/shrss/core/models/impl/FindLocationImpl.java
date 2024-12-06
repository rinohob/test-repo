package com.shrss.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;
import com.shrss.core.models.FindLocation;
import com.shrss.core.utils.LinkUtils;

import lombok.Getter;

/**
 * The Class FindLocationImpl.
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class }, adapters = FindLocation.class, resourceType = {
		FindLocationImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class FindLocationImpl implements FindLocation {

	/** The find location resource type. */
	protected static final String RESOURCE_TYPE = "shrss/components/findLocation";

	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The location label. */
	@ValueMapValue
	@Getter
	private String locationLabel;

	/** The placeholder text. */
	@ValueMapValue
	@Getter
	private String placeholderText;

	/** The search text. */
	@ValueMapValue
	@Getter
	private String searchText;

	/** The view all text. */
	@ValueMapValue
	@Getter
	private String viewAllText;

	/** The empty text. */
	@ValueMapValue
	@Getter
	private String emptyText;

	/** The page path. */
	@ValueMapValue
	private String pagePath;

	/**
	 * Gets the page path.
	 *
	 * @return the page path
	 */
	public String getPagePath() {
		return LinkUtils.getFormattedLink(pagePath, resourceResolver);
	}

}
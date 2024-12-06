package com.shrss.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.adobe.acs.commons.models.injectors.annotation.HierarchicalPageProperty;
import com.adobe.cq.export.json.ContainerExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.models.Page;
import com.adobe.cq.wcm.core.components.models.datalayer.ComponentData;
import com.day.cq.commons.Externalizer;

/**
 * The Class BasePageModel.
 */
@Model(adaptables = { SlingHttpServletRequest.class }, adapters = { Page.class,
		ContainerExporter.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = BasePageModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class BasePageModel  {

	/** The Constant HTML_EXTENSION. */
	private static final String HTML_EXTENSION = ".html";

	/** The Constant RESOURCE_TYPE. */
	public static final String RESOURCE_TYPE = "shrss/components/structure/page";

	/** The Constant PN_SUBCLIENTLIB. */
	public static final String PN_SUBCLIENTLIB = "subClientLib";

	/** The Constant PN_MAINCLIENTLIB. */
	public static final String PN_MAINCLIENTLIB = "mainClientLib";

	/** The Constant DEFAULT_SUBCLIENTLIB. */
	public static final String DEFAULT_SUBCLIENTLIB = "page-none";

	/** The Constant DEFAULT_MAINCLIENTLIB. */
	public static final String DEFAULT_MAINCLIENTLIB = "hardrock.site";

	/** The component data. */
	ComponentData componentData;

	/** The page url. */
	private String pageUrl;

	/** The externalizer. */
	@OSGiService
	Externalizer externalizer;

	/** The resource resolver. */
	@SlingObject
	public ResourceResolver resourceResolver;

	/** The current page. */
	@ScriptVariable(injectionStrategy = InjectionStrategy.OPTIONAL)
	private com.day.cq.wcm.api.Page currentPage;

	/** The resource. */
	@SlingObject
	protected Resource resource;

	/**
	 * Gets the page url.
	 *
	 * @return the page url
	 */
	public String getPageUrl() {
		if (null != resourceResolver && null != resourceResolver.adaptTo(Externalizer.class)) {
			externalizer = resourceResolver.adaptTo(Externalizer.class);
			pageUrl = externalizer.publishLink(resourceResolver, currentPage.getPath()) + HTML_EXTENSION;
		}
		return pageUrl;
	}

	/** The sub client lib. */
	@HierarchicalPageProperty(PN_SUBCLIENTLIB)
	String subClientLib;

	/**
	 * Gets the sub client lib.
	 *
	 * @return the sub client lib
	 */
	public String getSubClientLib() {
		return StringUtils.isBlank(subClientLib) ? DEFAULT_SUBCLIENTLIB : subClientLib;
	}

	/** The main client lib. */
	@HierarchicalPageProperty(PN_MAINCLIENTLIB)
	String mainClientLib;

	/**
	 * Gets the main client lib.
	 *
	 * @return the main client lib
	 */
	public String getMainClientLib() {
		return StringUtils.isBlank(mainClientLib) ? DEFAULT_MAINCLIENTLIB : mainClientLib;
	}

}

package com.shrss.core.models.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.wcm.api.Page;
import com.shrss.core.models.CrownNavModel;
import com.shrss.core.models.CrownNavigationItem;
import com.shrss.core.utils.LinkUtils;

import lombok.Getter;


/**
 * Crown Navigation Model Impl
 * 
 * @author Adobe
 *
 */

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class },
resourceType = { CrownNavModelImpl.RESOURCE_TYPE },
adapters = {
		CrownNavModel.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CrownNavModelImpl implements CrownNavModel {
	
	protected static final String RESOURCE_TYPE = "shrss/components/crownnav";
	
	/** The lob links. */
	@ChildResource (name = "lobLinks")
	@Getter
	private List<CrownNavigationItem> lobLinks;

	/**
	 * The current page
	 */
	@ScriptVariable
	private Page currentPage;

	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	/**
	 * Inits the.
	 */
	@PostConstruct
	private void init() {
		if (null != lobLinks && null != currentPage ) {
			for (CrownNavigationItem crownNavItem : lobLinks) {
				if (currentPage.getPath().equals(crownNavItem.getNavUrl())) {
					crownNavItem.setActive(true);
				}
				crownNavItem.setNavUrl(LinkUtils.getFormattedLink(crownNavItem.getNavUrl(), resourceResolver));
			}
		}
	}
}

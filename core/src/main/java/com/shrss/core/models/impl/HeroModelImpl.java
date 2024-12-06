package com.shrss.core.models.impl;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;
import com.shrss.core.models.HeroModel;

import lombok.Getter;

/**
 * The Class HeroModelImpl.
 */
@Model(
	    adaptables = Resource.class,
	    adapters = { HeroModel.class },
	    resourceType = { HeroModelImpl.RESOURCE_TYPE },
	    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class HeroModelImpl implements HeroModel {
	
	/** The Constant RESOURCE_TYPE. */
	protected static final String RESOURCE_TYPE = "shrss/components/hero";
	
	/** The cta list. */
	@ChildResource (name = "actions")
	@Getter
	private List<HeroCtaItem> ctaList;
	
	/** The asset type. */
	@ValueMapValue
	@Getter
	private String assetType;
	
	/** The pretitle. */
	@ValueMapValue
	@Getter
	private String pretitle;
	
	/** The alt. */
	@ValueMapValue
	@Getter
	private String alt;
	
	/** The title. */
	@ValueMapValue(name="jcr:title")
	@Getter
	private String title;
	
	/** The description. */
	@ValueMapValue(name="jcr:description")
	@Getter
	private String description;
	
	/** The file reference. */
	@ValueMapValue
	@Getter
	private String fileReference;
	
	/** The id. */
	@ValueMapValue
	@Getter
	private String id;
	
	@ValueMapValue(name="class")
	@Getter
	private String cssClass;
}

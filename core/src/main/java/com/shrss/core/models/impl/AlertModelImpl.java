package com.shrss.core.models.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;
import com.shrss.core.models.AlertModel;

import lombok.Getter;

@Model(
	    adaptables = Resource.class,
	    adapters = { AlertModel.class },
	    resourceType = { AlertModelImpl.RESOURCE_TYPE },
	    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class AlertModelImpl implements AlertModel {
	
	protected static final String RESOURCE_TYPE = "shrss/components/alert";
	
	@ValueMapValue
	@Getter
	private String fromDate;
	
	@ValueMapValue
	@Getter
	private String toDate;
	
	@ValueMapValue
	@Getter
	private String text;
	
	@ValueMapValue
	@Getter
	private String mobileText;
	
	@ValueMapValue
	@Getter
	private boolean dismissAlert;
	
	@ValueMapValue
	@Getter
	private String showHideIcon;
	
	@ValueMapValue
	@Getter
	private String ariaLabel;
	
	@ValueMapValue(name="class")
	@Getter
	private String cssclass;
	
	@ValueMapValue
	@Getter
	private String id;
	
	@ValueMapValue(name="cq:tags")
	@Getter
	private String[] tags;
}

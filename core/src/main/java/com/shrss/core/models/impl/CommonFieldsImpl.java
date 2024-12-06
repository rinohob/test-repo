package com.shrss.core.models.impl;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;
import com.shrss.core.models.CommonFields;

/**
 * Common Dialog Fields Sling Model Implementation Class
 */

@Model(adaptables = { Resource.class }, adapters = {
		CommonFields.class },
resourceType = { CommonFieldsImpl.RESOURCE_TYPE },
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CommonFieldsImpl implements CommonFields {

	protected static final String RESOURCE_TYPE = "shrss/components/commonfields";
	
	/**
	 * The id
	 */
	@ValueMapValue
	private String id;

	/**
	 * The class
	 */
	@ValueMapValue (name = "class")
	private String overrideClass;

	/**
	 * Returns the id of the component.
	 *
	 * @return id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Returns the overriding css class for the component.
	 *
	 * @return overrideClass
	 */
	@Override
	public String getOverrideClass() {
		return overrideClass;
	}

}

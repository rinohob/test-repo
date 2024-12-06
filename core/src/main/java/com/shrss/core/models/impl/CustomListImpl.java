package com.shrss.core.models.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;
import com.shrss.core.models.CustomList;

/**
 * The Class CustomListImpl.
 */
@Model(adaptables = { Resource.class }, adapters = CustomList.class, resourceType = {
		CustomListImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CustomListImpl implements CustomList {

	/** The Constant RESOURCE_TYPE. */
	protected static final String RESOURCE_TYPE = "shrss/components/list";

	/** The columns. */
	@ValueMapValue
	private String columns;

	/**
	 * Gets the columns.
	 *
	 * @return the columns
	 */
	public String getColumns() {
		return columns;
	}

}
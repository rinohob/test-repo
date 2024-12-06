package com.shrss.core.models.impl;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;
import com.shrss.core.models.Title;


/**
 * The Class TitleImpl.
 */
@Model(
    adaptables = { Resource.class },
    adapters = {
        Title.class },
    	resourceType = { TitleImpl.RESOURCE_TYPE },
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class TitleImpl implements com.adobe.cq.wcm.core.components.models.Title, Title {

	protected static final String RESOURCE_TYPE = "shrss/components/title";
	
    /**
     * This retrieves the eyebrow value
     */
    @ValueMapValue (name = "eyebrow")
    private String eyebrow;
    
    /**
     * This retrieves the eyebrow check value
     */
    @ValueMapValue(name="eyebrowcheck")
    private String eyebrowCheck;
    

    /**
     * Gets the eyebrow.
     *
     * @return the eyebrow
     */
    @Override
    public String getEyebrow() {
        return eyebrow;
    }

    /**
     * Gets the eyebrow check.
     *
     * @return the eyebrow check
     */
    @Override
    public String getEyebrowCheck() {
        return eyebrowCheck;
    }

}

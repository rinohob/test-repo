package com.shrss.core.models.impl;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import com.adobe.cq.export.json.ExporterConstants;
import com.shrss.core.models.CrownCTAItem;
import com.shrss.core.models.CrownCTAModel;

import lombok.Getter;


/**
 * Model class for Crown Navigation
 * 
 * @author Adobe
 *
 */
@Model(
    adaptables = Resource.class,
    adapters = { CrownCTAModel.class },
    resourceType = { CrownCTAModelImpl.RESOURCE_TYPE },
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class CrownCTAModelImpl implements CrownCTAModel {
    
	protected static final String RESOURCE_TYPE = "shrss/components/crowncta";
	
    /** The cta item list. */
    @ChildResource(name = "crowncta")
    @Getter
    private List<CrownCTAItem> ctaItemList;
}
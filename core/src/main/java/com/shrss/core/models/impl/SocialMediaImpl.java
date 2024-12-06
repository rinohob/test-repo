package com.shrss.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import com.adobe.cq.export.json.ExporterConstants;
import com.shrss.core.models.SocialMedia;
import com.shrss.core.models.SocialMediaItems;

import lombok.Getter;

/**
 * 
 * The SocialMediaImpl class
 *
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class }, adapters = SocialMedia.class, resourceType = {
		SocialMediaImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class SocialMediaImpl implements SocialMedia {

	/**
	 * The social media resource type
	 */
	protected static final String RESOURCE_TYPE = "shrss/components/socialmedia";

	/**
	 * The list of SocialMediaItems
	 */
	@ChildResource
	@Getter
	private List<SocialMediaItems> socialMediaLinks;

}
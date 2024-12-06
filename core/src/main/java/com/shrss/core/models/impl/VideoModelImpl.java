package com.shrss.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.shrss.core.models.VideoModel;

import lombok.Getter;

/**
 * The Class VideoModelImpl.
 */
@Model(adaptables = { SlingHttpServletRequest.class, Resource.class }, adapters = { VideoModel.class }, resourceType = {
		VideoModelImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VideoModelImpl implements VideoModel {

	/**
	 * The constant RESOURCE_TYPE.
	 */
	protected static final String RESOURCE_TYPE = "shrss/components/video";

	/** The external url. */
	@ValueMapValue
	@Getter
	private String externalUrl;

	/** The type. */
	@ValueMapValue
	@Getter
	private String type;
	
	/** The width. */
	@ValueMapValue
	@Getter
	private String externalWidth;

}

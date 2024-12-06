package com.shrss.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.shrss.core.utils.LinkUtils;


/**
 * The Class MainNavigationLogo.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MainNavigationLogo {

	/**
	 * The resourceResolver
	 */
	@SlingObject
	private ResourceResolver resourceResolver;

	/**
	 * The fileReference
	 */
	@ValueMapValue
	private String fileReference;

	/**
	 * The logoUrl
	 */
	@ValueMapValue
	private String logoUrl;

	/**
	 * The logoTarget
	 */
	@ValueMapValue
	private String logoTarget;

	/**
	 * The logoText
	 */
	@ValueMapValue
	private String logoText;

	/**
	 * Get the logoUrl
	 * 
	 * @return logoUrl
	 */
	public String getLogoUrl() {
		return LinkUtils.getFormattedLink(logoUrl, resourceResolver);
	}

	/**
	 * Get the logoTarget
	 * 
	 * @return logoTarget
	 */
	public String getLogoTarget() {
		return logoTarget;
	}

	/**
	 * Get the logoText
	 * 
	 * @return logoText
	 */
	public String getLogoText() {
		return logoText;
	}

	/**
	 * Get the fileReference
	 * 
	 * @return fileReference
	 */
	public String getFileReference() {
		return fileReference;
	}
}

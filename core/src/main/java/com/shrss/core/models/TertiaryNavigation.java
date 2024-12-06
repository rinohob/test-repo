package com.shrss.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.shrss.core.utils.LinkUtils;


/**
 * The Class TertiaryNavigation.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TertiaryNavigation {

	/**
	 * The tertiaryLabel
	 */
	@ValueMapValue
	private String tertiaryLabel;

	/**
	 * The tertiaryUrl
	 */
	@ValueMapValue
	private String tertiaryUrl;

	/**
	 * The tertiaryTarget
	 */
	@ValueMapValue
	private String tertiaryTarget;

	/**
	 * The tertiaryShowHide
	 */
	@ValueMapValue
	private String tertiaryShowHide;

	/**
	 * The ResourceResolver
	 */
	@SlingObject
	private ResourceResolver resourceResolver;

	/**
	 * Get the tertiaryLabel
	 * 
	 * @return tertiaryLabel
	 */
	public String getTertiaryLabel() {
		return tertiaryLabel;
	}

	/**
	 * Get the tertiaryUrl
	 * 
	 * @return tertiaryUrl
	 */
	public String getTertiaryUrl() {
		return LinkUtils.getFormattedLink(tertiaryUrl, resourceResolver);
	}

	/**
	 * Get the tertiaryTarget
	 * 
	 * @return tertiaryTarget
	 */
	public String getTertiaryTarget() {
		return tertiaryTarget;
	}

	/**
	 * Get the tertiaryShowHide
	 * 
	 * @return tertiaryShowHide
	 */
	public String getTertiaryShowHide() {
		return tertiaryShowHide;
	}

}
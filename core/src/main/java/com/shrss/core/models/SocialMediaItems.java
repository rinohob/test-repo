package com.shrss.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.shrss.core.utils.LinkUtils;

import lombok.Getter;

/**
 * The Class SocialMediaItems.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SocialMediaItems {

	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The socialMedia. */
	@ValueMapValue
	@Getter
	private String socialMedia;

	/** The pagePath. */
	@ValueMapValue
	private String pagePath;

	/** The ctaTarget. */
	@ValueMapValue
	@Getter
	private String ctaTarget;
	
	@ValueMapValue
	@Getter
	private String label;

	/**
	 * Gets the page path.
	 *
	 * @return the page path
	 */
	public String getPagePath() {
		return LinkUtils.getFormattedLink(pagePath, resourceResolver);
	}

}
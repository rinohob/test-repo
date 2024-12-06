package com.shrss.core.models.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.shrss.core.utils.LinkUtils;

import lombok.Getter;

/**
 * The Class HeroCtaItem.
 */
@Model(
		  adaptables = Resource.class,
		  adapters = { HeroCtaItem.class },
		  defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)
public class HeroCtaItem {
	
	/** The text. */
	@Getter
	@ValueMapValue
	private String text;
	
	/** The link. */
	@ValueMapValue	
	private String link;
	
	/** The link target. */
	@Getter
	@ValueMapValue
	private String linkTarget;
	
	/** The resource resolver. */
	@SlingObject
	ResourceResolver resourceResolver;
	
	/**
	 * Gets the link.
	 *
	 * @return the link
	 */
	public String getLink() {
		return LinkUtils.getFormattedLink(link, resourceResolver);
	}

}

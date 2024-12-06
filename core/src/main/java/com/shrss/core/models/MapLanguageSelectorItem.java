package com.shrss.core.models;

import com.shrss.core.utils.LinkUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import lombok.Getter;


/**
 * The Class MapLanguageSelectorItem.
 */
@Model(
		  adaptables = Resource.class,
		  adapters = { MapLanguageSelectorItem.class },
		  defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)
public class MapLanguageSelectorItem {

	/**
	 * The resourceResolver
	 */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The label dropdown. */
	@Getter
	@ValueMapValue
	public String labelDropdown;
	
	/** The url. */
	@ValueMapValue
	public String url;

	/** The new tab dropdown. */
	@Getter
	@ValueMapValue
	public String newTabDropdown;
	
	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return LinkUtils.getFormattedLink(url, resourceResolver);
	}
}

package com.shrss.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.shrss.core.utils.LinkUtils;


/**
 * The Class SecondaryNavigation.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SecondaryNavigation {

	/**
	 * The tertiaryNav
	 */
	@ChildResource
	private List<TertiaryNavigation> tertiaryNav;

	/**
	 * The showHide
	 */
	@ValueMapValue
	private String showHide;

	/**
	 * The urlTarget
	 */
	@ValueMapValue
	private String urlTarget;

	/**
	 * The url
	 */
	@ValueMapValue
	private String url;

	/**
	 * The label
	 */
	@ValueMapValue
	private String label;

	/**
	 * The ResourceResolver
	 */
	@SlingObject
	private ResourceResolver resourceResolver;

	/**
	 * Get the list of tertiaryNav items
	 * 
	 * @return list of tertiaryNav items
	 */
	public List<TertiaryNavigation> getTertiaryNav() {
		if (tertiaryNav != null) {
			return Collections.unmodifiableList(tertiaryNav);
		}
		return new ArrayList<>();
	}

	/**
	 * Get showHide
	 * 
	 * @return showHide
	 */
	public String getShowHide() {
		return showHide;
	}

	/**
	 * Get the urlTarget
	 * 
	 * @return urlTarget
	 */
	public String getUrlTarget() {
		return urlTarget;
	}

	/**
	 * Get the url
	 * 
	 * @return url
	 */
	public String getUrl() {
		return LinkUtils.getFormattedLink(url, resourceResolver);
	}

	/**
	 * Get the label
	 * 
	 * @return label
	 */
	public String getLabel() {
		return label;
	}

}
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
 * The Class PrimaryNavigation.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrimaryNavigation {

	/**
	 * The ResourceResolver
	 */
	@SlingObject
	private ResourceResolver resourceResolver;

	/**
	 * The label
	 */
	@ValueMapValue
	private String label;

	/**
	 * The url
	 */
	@ValueMapValue
	private String url;

	/**
	 * The urlTarget
	 */
	@ValueMapValue
	private String urlTarget;

	/**
	 * The showHide
	 */
	@ValueMapValue
	private String showHide;

	/**
	 * The secondaryNav list
	 */
	@ChildResource
	private List<SecondaryNavigation> secondaryNav;

	/**
	 * Get the label
	 * 
	 * @return label
	 */
	public String getLabel() {
		return label;
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
	 * Get the urlTarget
	 * 
	 * @return urlTarget
	 */
	public String getUrlTarget() {
		return urlTarget;
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
	 * Get list of secondaryNav items
	 * 
	 * @return secondaryNav
	 */
	public List<SecondaryNavigation> getSecondaryNav() {
		if (secondaryNav != null) {
			return Collections.unmodifiableList(secondaryNav);
		}
		return new ArrayList<>();
	}

}
package com.shrss.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import lombok.Getter;
import lombok.Setter;


/**
 * The Class CrownNavigationItem.
 */
@Model(
  adaptables = Resource.class,
  adapters = { CrownNavigationItem.class },
  defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CrownNavigationItem {

	/**
	 * The resourceResolver
	 */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The label. */
	@Getter
	@ValueMapValue
	private String label;

	/** The nav url. */
	@ValueMapValue
	@Getter
	@Setter
	private String navUrl;
	
	/** The new tab. */
	@Getter
	@ValueMapValue
	private String newTab;
	
	/** The show hide link. */
	@Getter
	@ValueMapValue
	private String showHideLink;
	
	/** The is active. */
	@Getter
	@Setter
	private boolean isActive;
}

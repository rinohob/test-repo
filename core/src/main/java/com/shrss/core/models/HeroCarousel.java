package com.shrss.core.models;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.wcm.core.components.models.Carousel;

import lombok.Getter;

/**
 * 
 * The HeroCarousel model
 *
 */
@Model(adaptables = { Resource.class }, adapters = { Carousel.class,
		HeroCarousel.class }, resourceType = "shrss/components/herocarousel", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroCarousel implements Carousel {

	/**
	 * The resource
	 */
	@SlingObject
	protected Resource resource;

	/**
	 * The activeItem
	 */
	@ValueMapValue
	private String activeItem;
	
	/**
	 * The slideIndicator
	 */
	@ValueMapValue
	@Getter
	private String slideIndicator;

	/**
	 * The startIndex
	 */
	private String startIndex;

	/**
	 * The resourceList
	 */
	ArrayList<String> resourceList = new ArrayList<>();

	/**
	 * Sets the startIndex
	 */
	@PostConstruct
	private void init() {
		if (null != resource && resource.hasChildren()) {
			Iterable<Resource> childResources = resource.getChildren();
			for (Resource childResource : childResources) {
				if (null != childResource) {
					resourceList.add(childResource.getName());
				}
			}
		}
		if (resourceList.indexOf(activeItem) < 0) {
			startIndex = Integer.toString(0);
		} else {
			startIndex = Integer.toString(resourceList.indexOf(activeItem));
		}
	}

	/**
	 * Get the startIndex
	 * 
	 * @return startIndex
	 */
	public String getStartIndex() {
		return startIndex;
	}

}

package com.shrss.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.wcm.core.components.models.Navigation;
import com.day.cq.wcm.api.Page;
import com.shrss.core.utils.LinkUtils;

import lombok.Getter;

/**
 * Sling Model for the Sub Nav component.
 */
@Model(adaptables = { SlingHttpServletRequest.class, Resource.class }, adapters = { Navigation.class,
		SubNavModel.class }, resourceType = "shrss/components/sub-nav", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SubNavModel implements Navigation {

	/**
	 * The current page
	 */
	@ScriptVariable
	private Page currentPage;
	/**
	 * The SubNavItemsModel list
	 */
	@ChildResource(name = "subNavItems")
	@Getter
	private List<SubNavItemsModel> subNavItems;

	/**
	 * The mobileSubnav
	 */
	@ValueMapValue
	@Getter
	private String mobileSubnav;

	/**
	 * The accessLabel
	 */
	@ValueMapValue(name = "accessibilityLabel")
	@Getter
	private String accessLabel;

	/**
	 * The ResourceResolver
	 */
	@SlingObject
	private ResourceResolver resourceResolver;

	/**
	 * Inits the.
	 */
	@PostConstruct
	private void init() {
		if (null != subNavItems && null != currentPage && null != resourceResolver) {
			for (SubNavItemsModel subNavItemsModel : subNavItems) {
				if (null != subNavItemsModel) {
					if (currentPage.getPath().equals(subNavItemsModel.getPagePath())) {
						subNavItemsModel.setActive(true);
					}
					subNavItemsModel
							.setPagePath(LinkUtils.getFormattedLink(subNavItemsModel.getPagePath(), resourceResolver));
				}
			}
		}
	}
}

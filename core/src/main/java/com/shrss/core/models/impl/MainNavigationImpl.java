package com.shrss.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;
import com.shrss.core.models.CrownCTAItem;
import com.shrss.core.models.CrownNavigationItem;
import com.shrss.core.models.MainNavigation;
import com.shrss.core.models.MainNavigationLogo;
import com.shrss.core.models.PrimaryNavigation;
import com.shrss.core.utils.LinkUtils;

@Model(adaptables = { Resource.class, SlingHttpServletRequest.class }, adapters = MainNavigation.class, resourceType = {
		MainNavigationImpl.RESOURCE_TYPE }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class MainNavigationImpl implements MainNavigation {

	/**
	 * The main navigation resource type
	 */
	protected static final String RESOURCE_TYPE = "shrss/components/mainnavigation";

	/**
	 * The list of primaryNav items
	 */
	@ChildResource
	private List<PrimaryNavigation> primaryNav;

	/**
	 * The list of logo items
	 */
	@ChildResource
	private List<MainNavigationLogo> logo;

	/**
	 * The mobileText
	 */

	@ValueMapValue

	@Default(values = "Main Menu")

	private String mobileText;

	@SlingObject
	private ResourceResolver resourceResolver;

	private static final String CROWN_CTA = "/right/crowncta";

	private static final String CROWN_NAV = "/left/lobLinks";

	@SlingObject
	SlingHttpServletRequest req;

	/**
	 * Get the primaryNav items
	 */
	public List<PrimaryNavigation> getPrimaryNav() {
		if (primaryNav != null) {
			return Collections.unmodifiableList(primaryNav);
		}
		return new ArrayList<>();
	}

	/**
	 * Get the logo items
	 */
	public List<MainNavigationLogo> getLogo() {
		if (logo != null) {
			return Collections.unmodifiableList(logo);
		}
		return new ArrayList<>();
	}

	/**
	 * get the CTA Item list for small screen devices
	 * 
	 * @return List of CTA Items
	 */
	public List<CrownCTAItem> getCtaItemList() {
		List<CrownCTAItem> ctaItemList = new ArrayList<>();
		if (null != req && null != req.getResource() && null != req.getResource().getParent() && null != req.getResource().getParent().getPath()) {
			String parentPath = req.getResource().getParent().getPath();
			if (null != resourceResolver) {
				Resource crownCTAResource = resourceResolver.getResource(parentPath + CROWN_CTA);
				if (null != crownCTAResource) {
					Iterator<Resource> listIterator = crownCTAResource.listChildren();
					while (listIterator.hasNext()) {
						Resource resource = listIterator.next();
						if (null != resource) {
							CrownCTAItem crownCTAItem = resource.adaptTo(CrownCTAItem.class);
							if (null != crownCTAItem) {
								ctaItemList.add(crownCTAItem);
							}
						}
					}
				}
			}
		}
		return ctaItemList;
	}

	/**
	 * get the Crown Navigation List for small screens
	 * 
	 * @return List of Crown Navigation Item
	 */
	public List<CrownNavigationItem> getLobLinks() {
		List<CrownNavigationItem> lobLinks = new ArrayList<>();
		if (null != req && null != req.getResource() && null != req.getResource().getParent() && null != req.getResource().getParent().getPath()) {
			String parentPath = req.getResource().getParent().getPath();
			if (null != resourceResolver) {
				Resource crownNavigationResource = resourceResolver.getResource(parentPath + CROWN_NAV);

				if (null != crownNavigationResource) {
					Iterator<Resource> listIterator = crownNavigationResource.listChildren();
					while (listIterator.hasNext()) {
						Resource resource = listIterator.next();
						if (null != resource) {
							CrownNavigationItem crownNavigationItem = resource.adaptTo(CrownNavigationItem.class);
							if (null != crownNavigationItem) {
								crownNavigationItem.setNavUrl(LinkUtils.getFormattedLink(crownNavigationItem.getNavUrl(), resourceResolver));
								lobLinks.add(crownNavigationItem);
							}
						}

					}
				}
			}
		}
		return lobLinks;
	}

	/**
	 * 
	 * Get the mobile Text
	 * 
	 * @return mobileText
	 * 
	 */

	public String getMobileText() {
		return mobileText;
	}

}
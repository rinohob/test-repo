package com.shrss.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.shrss.core.utils.LinkUtils;

import lombok.Getter;


/**
 * The Class CrownCTAItem.
 */
@Model(adaptables = Resource.class, adapters = {
		CrownCTAItem.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CrownCTAItem {

	/**
	 * The resourceResolver
	 */
	@SlingObject
	private ResourceResolver resourceResolver;

	/** The label. */
	@Getter
	@ValueMapValue
	private String label;

	/** The cta URL. */
	@ValueMapValue
	private String ctaURL;

	/** The new tab. */
	@Getter
	@ValueMapValue
	private String newTab;
	
	/** The color sel. */
	@Getter
	@ValueMapValue
	private String colorSel;

	/** The experience select. */
	@Getter
	@ValueMapValue
	private String experienceSelect;

	/** The file reference. */
	@Getter
	@ValueMapValue
	private String fileReference;
	
	/** The author id. */
	@Getter
	@ValueMapValue
	private String authorId;
	
	/** The dropdown for. */
	@Getter
	@ValueMapValue
	private String dropdownFor;
	
	/** The map lang item list. */
	@Getter
    @ChildResource(name="mapLangSel")
    private List<MapLanguageSelectorItem> mapLangItemList;

	/**
	 * Gets the cta URL.
	 *
	 * @return the cta URL
	 */
	public String getCtaURL() {
		return LinkUtils.getFormattedLink(ctaURL, resourceResolver);
	}

}

package com.shrss.core.models;

import java.util.List;

import com.shrss.core.models.impl.HeroCtaItem;

/**
 * The Interface HeroModel.
 */
public interface HeroModel {

	/**
	 * Gets the cta list.
	 *
	 * @return the cta list
	 */
	public List<HeroCtaItem> getCtaList();
	
	/**
	 * Gets the asset type.
	 *
	 * @return the asset type
	 */
	public String getAssetType();
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle();
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription();
	
	/**
	 * Gets the pretitle.
	 *
	 * @return the pretitle
	 */
	public String getPretitle();
	
	/**
	 * Gets the file reference.
	 *
	 * @return the file reference
	 */
	public String getFileReference();
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId();

	/**
	 * Gets the alt text.
	 *
	 * @return the alt text
	 */
	public String getAlt();
	
	public String getCssClass();
}

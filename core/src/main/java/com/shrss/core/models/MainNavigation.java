package com.shrss.core.models;

import java.util.List;


/**
 * The Interface MainNavigation.
 */
public interface MainNavigation {

	/**
	 * Get the list of PrimaryNavigation items
	 * 
	 * @return list of PrimaryNavigation items
	 */
	public List<PrimaryNavigation> getPrimaryNav();

	/**
	 * Get the list of MainNavigationLogo items
	 * 
	 * @return list of MainNavigationLogo items
	 */
	public List<MainNavigationLogo> getLogo();

	/**
	 * Get the mobileText
	 * 
	 * @return mobileText
	 */
	public String getMobileText();
	
	/**
	 * get the CTA Item list for small screen devices
	 * @return List of CTA Items
	 */
	public List<CrownCTAItem> getCtaItemList();
	
	/**
	 * get the  Crown Navigation List for small screens
	 * @return List of Crown Navigation Item
	 */ 
	public List<CrownNavigationItem> getLobLinks();
	
}
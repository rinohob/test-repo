package com.shrss.core.models;

import java.util.Date;

/**
 * The Interface AlertModel.
 */
public interface AlertModel {
	public String getFromDate();
	
	public String getToDate();
	
	public String getText();
	
	public String getMobileText();
	
	public boolean isDismissAlert();
	
	public String getShowHideIcon();
	
	public String getAriaLabel();
	
	public String getCssclass();
	
	public String getId();
	
	public String[] getTags();

}

package com.shrss.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class LinkUtils {
	/**
	 * The Logger for this class
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkUtils.class);

	/**
	 * private constructor in the util class to hide the implicit public one.
	 */
	private LinkUtils() {

	}

	/**
	 * Get the formatted link
	 * 
	 * @param url
	 * @param resourceResolver
	 * @return formatted link
	 */
	public static String getFormattedLink(final String url, final ResourceResolver resourceResolver) {
		if (StringUtils.isBlank(url))
			return StringUtils.EMPTY;

		if (null != resourceResolver && getInternalPage(url, resourceResolver) != null) {
			final String mappedURL = resourceResolver.map(url);
			return (mappedURL.length() > 1) ? (mappedURL + ".html") : url;
		} else if (isExternalLink(url)) {
			return url;
		}
		return url;
	}

	/**
	 * Checks if it is an external link or not
	 * 
	 * @param link
	 * @return external link true or false
	 */
	public static boolean isExternalLink(String link) {
		return link.startsWith("http") || link.startsWith("https");
	}

	/**
	 * Get the internal aem page
	 * 
	 * @param url
	 * @param resourceResolver
	 * @return page
	 */
	public static Page getInternalPage(final String url, final ResourceResolver resourceResolver) {
		if (StringUtils.isBlank(url))
			return null;
		if (null != resourceResolver) {
			final PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
			if (pageManager != null) {
				final Page page = pageManager.getPage(url);
				if (page != null) {
					return page;
				}
			}
		}
		return null;
	}
}
package com.shrss.core.models;

import static com.day.cq.wcm.api.constants.NameConstants.NN_CONTENT;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.acs.commons.models.injectors.annotation.AemObject;
import com.adobe.cq.wcm.core.components.util.LocalizationUtils;
import com.adobe.cq.xf.ExperienceFragmentsConstants;
import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;
import com.day.cq.commons.inherit.InheritanceValueMap;
import com.day.cq.wcm.api.LanguageManager;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.msm.api.LiveRelationshipManager;
import com.day.text.Text;

@Model(adaptables = { Resource.class,
		SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CustomHearderFooterExpFragment {

	private static final String FFRAGMENT_VARIATION_PATH = "ffragmentVariationPath";

	private static final String HFRAGMENT_VARIATION_PATH = "hfragmentVariationPath";

	private static final String HEADER = "header";

	private static final String FOOTER = "footer";

	@AemObject
	private Page currentPage;

	@Self
	private SlingHttpServletRequest request;

	@SlingObject
	private ResourceResolver resourceResolver;

	@OSGiService
	private LanguageManager languageManager;

	@OSGiService
	private LiveRelationshipManager relationshipManager;

	String expFragmentPath;

	@ValueMapValue
	private String fragmentVariationPath;

	@ValueMapValue(name = "xftype")
	private String xfType;

	private boolean isPropertyAvailable = false;
	private boolean isConfigured = false;
	private static final String CONTENT_ROOT = "/content";
	private static final char PATH_DELIMITER_CHAR = '/';

	public boolean isPropertyAvailable() {
		return isPropertyAvailable;
	}

	public boolean isConfigured() {
		return isConfigured;
	}

	public String getExpFragmentPath() {
		return expFragmentPath;
	}

	@PostConstruct
	public void init() {
		Resource res = currentPage.getContentResource();
		InheritanceValueMap map = new HierarchyNodeInheritanceValueMap(res);
		String localizedFragmentVariationPath = null;
		if (res != null) {
			if (null != xfType) {
				if (StringUtils.equals(xfType, HEADER)) {
					localizedFragmentVariationPath = map.getInherited(HFRAGMENT_VARIATION_PATH, String.class);
				} else if (StringUtils.equals(xfType, FOOTER)) {
					localizedFragmentVariationPath = map.getInherited(FFRAGMENT_VARIATION_PATH, String.class);
				}
			}
			if (!StringUtils.isBlank(localizedFragmentVariationPath)) {
				isPropertyAvailable = true;
				isConfigured = true;
			}
			if (null != fragmentVariationPath && StringUtils.isNotBlank(fragmentVariationPath)) {
				isConfigured = true;
			}
			String currentPageRootPath = LocalizationUtils.getLocalizationRoot(res, resourceResolver, languageManager,
					relationshipManager);
			// we should use getLocalizationRoot instead of getXfLocalizationRoot once the
			// XF UI supports creating Live and Language Copies
			String xfRootPath = getXfLocalizationRoot(localizedFragmentVariationPath, currentPageRootPath);
			if (StringUtils.isNotEmpty(currentPageRootPath) && StringUtils.isNotEmpty(xfRootPath)) {
				String xfRelativePath = StringUtils.substring(localizedFragmentVariationPath, xfRootPath.length());
				String localizedXfRootPath = StringUtils.replace(currentPageRootPath, CONTENT_ROOT,
						ExperienceFragmentsConstants.CONTENT_PATH, 1);
				expFragmentPath = StringUtils.join(localizedXfRootPath, xfRelativePath, PATH_DELIMITER_CHAR,
						NN_CONTENT);
			}
		}
	}

	private String getXfLocalizationRoot(final String xfPath, final String currentPageRoot) {
		if (StringUtils.isNotEmpty(xfPath) && StringUtils.isNotEmpty(currentPageRoot)
				&& resourceResolver.getResource(xfPath) != null
				&& resourceResolver.getResource(currentPageRoot) != null) {
			String[] xfPathTokens = Text.explode(xfPath, PATH_DELIMITER_CHAR);
			int xfRootDepth = Text.explode(currentPageRoot, PATH_DELIMITER_CHAR).length + 1;
			if (xfPathTokens.length >= xfRootDepth) {
				String[] xfRootTokens = new String[xfRootDepth];
				System.arraycopy(xfPathTokens, 0, xfRootTokens, 0, xfRootDepth);
				return StringUtils.join(PATH_DELIMITER_CHAR,
						Text.implode(xfRootTokens, Character.toString(PATH_DELIMITER_CHAR)));
			}
		}
		return null;
	}

}

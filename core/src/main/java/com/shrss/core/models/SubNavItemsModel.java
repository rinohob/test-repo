package com.shrss.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;


/**
 * Sling Model for the Sub Nav Items.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SubNavItemsModel {

    /**
     * The title
     */
    @ValueMapValue
    private String title;

    /**
     * The pagePath
     */
    @ValueMapValue
    private String pagePath;

    /**
     * The ctaTarget
     */
    @ValueMapValue
    private String ctaTarget;
    

    /** The is active. */
    private boolean isActive;

    /**
     * Get the title
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the pagePath
     * 
     * @return pagePath
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Sets the page path.
     *
     * @param pagePath the new page path
     */
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    /**
     * Get the ctaTarget
     * 
     * @return ctaTarget
     */
    public String getCtaTarget() {
        return ctaTarget;
    }

    /**
     * Checks if is active.
     *
     * @return true, if is active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active.
     *
     * @param isActive the new active
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

}
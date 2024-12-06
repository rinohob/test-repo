package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.shrss.core.models.CrownCTAItem;
import com.shrss.core.models.CrownCTAModel;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;


/**
 * The Class CrownCTAModelImplTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
@MockitoSettings(strictness = Strictness.LENIENT)
class CrownCTAModelImplTest {

    /** The aem context. */
    private final AemContext aemContext = new AemContext();
    
    /** The crown CTA model. */
    private CrownCTAModel crownCTAModel;

    /**
     * Sets the up.
     */
    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(CrownCTAModelImpl.class);
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        aemContext.load().json(absolutePath+"/com/shrss/core/models/crowncta.json",
                "/apps/shrss/components/crowncta");
        Resource currentResource = aemContext.currentResource("/apps/shrss/components/crowncta");
        crownCTAModel = currentResource.adaptTo(CrownCTAModelImpl.class);
    }

    /**
     * Test cta item list.
     */
    @Test
    void testCtaItemList() {
        List<CrownCTAItem> ctaItemList = crownCTAModel.getCtaItemList();
        assertEquals(2, ctaItemList.size(), "CTA item list size should match");

        CrownCTAItem firstItem = ctaItemList.get(0);
        assertEquals("https://unity.login.hardrock.com", firstItem.getCtaURL(), "First item CTA URL should match");
        assertEquals("true", firstItem.getNewTab(), "First item newTab should match");
        assertEquals("#ffffff", firstItem.getColorSel(), "First item colorSel should match");
        assertEquals("Sign in", firstItem.getLabel(), "First item label should match");
        assertEquals("CTA", firstItem.getDropdownFor(), "First item dropdownFor should match");
        assertEquals("help", firstItem.getExperienceSelect(), "First item experienceSelect should match");
        assertEquals("111", firstItem.getAuthorId(), "First item authorId should match");

        CrownCTAItem secondItem = ctaItemList.get(1);
        assertEquals("https://hotel.hardrock.com/", secondItem.getCtaURL(), "Second item CTA URL should match");
        assertEquals("true", secondItem.getNewTab(), "Second item newTab should match");
        assertEquals("#ffffff", secondItem.getColorSel(), "Second item colorSel should match");
        assertEquals("Book Now", secondItem.getLabel(), "Second item label should match");
        assertEquals("CTA", secondItem.getDropdownFor(), "Second item dropdownFor should match");
        assertEquals("dropdown", secondItem.getExperienceSelect(), "Second item experienceSelect should match");
        assertEquals("3333", secondItem.getAuthorId(), "Second item authorId should match");
    }
}

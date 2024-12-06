package com.shrss.core.models;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shrss.core.models.impl.CrownCTAModelImpl;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import io.wcm.testing.mock.aem.junit5.AemContext;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * The Class CrownCTAItemTest.
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class CrownCTAItemTest {

    /** The aem context. */
    private final AemContext aemContext = new AemContext();

    /** The crown CTA item. */
    private CrownCTAItem crownCTAItem;

    /**
     * Sets the up.
     */
    @BeforeEach
    void setUp() {
    	 aemContext.addModelsForClasses(CrownCTAModelImpl.class);
         Path resourceDirectory = Paths.get("src","test","resources");
         String absolutePath = resourceDirectory.toFile().getAbsolutePath();
         aemContext.load().json(absolutePath+"/com/shrss/core/models/crownCtaItem.json",
                 "/apps/shrss/components/crowncta");
         Resource currentResource = aemContext.currentResource("/apps/shrss/components/crowncta");
         crownCTAItem = currentResource.adaptTo(CrownCTAItem.class);
    }

    /**
     * Test label.
     */
    @Test
    void testLabel() {
        assertEquals("Book Now", crownCTAItem.getLabel(), "Label does not match");
    }

    /**
     * Test cta URL.
     */
    @Test
    void testCtaURL() {
        assertEquals("https://hotel.hardrock.com/", crownCTAItem.getCtaURL(), "CTA URL does not match");
    }

    /**
     * Test new tab.
     */
    @Test
    void testNewTab() {
        assertEquals("true", crownCTAItem.getNewTab(), "NewTab value does not match");
    }

    /**
     * Test color sel.
     */
    @Test
    void testColorSel() {
        assertEquals("#ffffff", crownCTAItem.getColorSel(), "Color selection does not match");
    }

    /**
     * Test experience select.
     */
    @Test
    void testExperienceSelect() {
        assertEquals("dropdown", crownCTAItem.getExperienceSelect(), "Experience selection does not match");
    }

    /**
     * Test file reference.
     */
    @Test
    void testFileReference() {
        assertEquals("/content/dam/example/image.jpg", crownCTAItem.getFileReference(), "File reference does not match");
    }

    /**
     * Test author id.
     */
    @Test
    void testAuthorId() {
        assertEquals("author123", crownCTAItem.getAuthorId(), "Author ID does not match");
    }

    /**
     * Test dropdown for.
     */
    @Test
    void testDropdownFor() {
        assertEquals("CTA", crownCTAItem.getDropdownFor(), "Dropdown for value does not match");
    }

    /**
     * Test map lang item list.
     */
    @Test
    void testMapLangItemList() {
        assertNotNull(crownCTAItem.getMapLangItemList(), "Map Language Item List is null");
        assertFalse(crownCTAItem.getMapLangItemList().isEmpty(), "Map Language Item List is empty");
    }
}

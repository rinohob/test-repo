package com.shrss.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AemContextExtension.class)
public class MapLanguageSelectorItemTest {

    private final AemContext aemContext = new AemContext();

    private MapLanguageSelectorItem mapLanguageSelectorItem;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(MapLanguageSelectorItem.class);
        Resource resource = aemContext.load().json("/com/shrss/core/models/maplanguageselector.json", "/content");
        Resource currentResource = aemContext.currentResource("/content/mapLangSel/item0");
        mapLanguageSelectorItem = currentResource.adaptTo(MapLanguageSelectorItem.class);
    }

    @Test
    void testLabelDropdown() {
        assertEquals("Portuguese", mapLanguageSelectorItem.getLabelDropdown());
    }

    @Test
    void testUrl() {
        assertEquals("", mapLanguageSelectorItem.getUrl());
    }

    @Test
    void testNewTabDropdown() {
        assertEquals("false", mapLanguageSelectorItem.getNewTabDropdown());
    }
}

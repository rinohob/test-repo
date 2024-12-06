package com.shrss.core.models.impl;

import com.shrss.core.models.CrownCTAItem;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class HeroCtaItemTest {

    private final AemContext aemContext = new AemContext();

    private HeroCtaItem heroCtaItem;

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(HeroCtaItem.class);
        Path resourceDirectory = Paths.get("src","test","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        aemContext.load().json(absolutePath+"/com/shrss/core/models/heroCtaItem.json",
                "/apps/shrss/components/herocta");
        Resource currentResource = aemContext.currentResource("/apps/shrss/components/herocta");
        heroCtaItem = currentResource.adaptTo(HeroCtaItem.class);
    }

    @Test
    void getLink() {
        assertEquals(heroCtaItem.getLink(),"/content/shrss/us/en/hard-rock");
    }

    @Test
    void getText() {
        assertEquals(heroCtaItem.getText(),"Learn More");
    }

    @Test
    void getLinkTarget() {
        assertEquals(heroCtaItem.getLinkTarget(),"_self");
    }
}
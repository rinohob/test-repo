package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shrss.core.models.HeroModel;
import com.shrss.core.models.impl.HeroCtaItem;
import com.shrss.core.models.impl.HeroModelImpl;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class HeroModelImplTest {

    private final AemContext aemContext = new AemContext();

    private HeroModel heroModel;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        aemContext.addModelsForClasses(HeroModelImpl.class);
        aemContext.load().json("/com/shrss/core/models/hero.json",
                "/content/shrss/us/en/home/jcr:content/root/container/container/hero");
        Resource currentResource = aemContext
                .currentResource("/content/shrss/us/en/home/jcr:content/root/container/container/hero");
        heroModel = currentResource.adaptTo(HeroModelImpl.class);

    }

    @Test
    void getCtaList() {
        List<HeroCtaItem> ctaItemList = heroModel.getCtaList();
        assertEquals(1, ctaItemList.size(), "CTA item list size should match");

        HeroCtaItem firstItem = ctaItemList.get(0);

        assertEquals(firstItem.getLink(), "/content/shrss/us/en/hard-rock");
        assertEquals(firstItem.getText(), "Learn More");
        assertEquals(firstItem.getLinkTarget(), "_self");

    }

    @Test
    void getAssetType() {
        assertEquals(heroModel.getAssetType(), "image");
    }

    @Test
    void getPretitle() {
        assertEquals(heroModel.getPretitle(), "<b>This is eyebro</b>");
    }

    @Test
    void getAlt() {
        assertEquals(heroModel.getAlt(), "image");
    }

    @Test
    void getTitle() {
        assertEquals(heroModel.getTitle(), "<b>This is Title</b>");
    }

    @Test
    void getDescription() {
        assertEquals(heroModel.getDescription(), "this is description");
    }

    @Test
    void getFileReference() {
        assertEquals(heroModel.getFileReference(), "/content/dam/shrss/summer-together-800x800.jpg");
    }

    @Test
    void getId() {
        assertEquals(heroModel.getId(), "123");
    }
    
    @Test
    void getCssClass() {
        assertEquals(heroModel.getCssClass(), "css-class");
    }
}
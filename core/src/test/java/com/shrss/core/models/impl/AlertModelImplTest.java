package com.shrss.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shrss.core.models.AlertModel;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AlertModelImplTest{

	private final AemContext aemContext = new AemContext();

	private AlertModel alertModel;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@BeforeEach
	void setUp() {
		aemContext.addModelsForClasses(AlertModelImpl.class);
		aemContext.load().json("/com/shrss/core/models/alert.json",
				"/content/shrss/us/en/home/jcr:content/root/container/container/alert");
		Resource currentResource = aemContext
				.currentResource("/content/shrss/us/en/home/jcr:content/root/container/container/alert");
		alertModel = currentResource.adaptTo(AlertModelImpl.class);
	}

	
	@Test
	void testText() {
		assertEquals(alertModel.getText(), "Alert1");
	}

	@Test
	void testDismissAlert() {
		assertEquals(alertModel.isDismissAlert(), false);
	}

	@Test
	void testShowHideIcon() {
		assertEquals(alertModel.getShowHideIcon(), "false");
	}

	@Test
	void testAreaLabel() {
		assertEquals(alertModel.getAriaLabel(), "Alert label");
	}

	@Test
	void testCssClass() {
		assertEquals(alertModel.getCssclass(), "custom-css");
	}

	@Test
	void testId() {
		assertEquals(alertModel.getId(), "123");
	}
	
	@Test
	void testFromDate() {
		assertEquals(alertModel.getFromDate(), "2024-08-24");
	}
	
	@Test
	void testToDate(){
		assertEquals(alertModel.getToDate(), "2024-08-24");
	}

	@Test
	void testTags(){
		assertEquals(alertModel.getTags()[0], "[shrss:hotel/new-york]");
	}

	@Test
	void testMobiletext() {
		assertEquals(alertModel.getMobileText(), "Mobile text");
	}
}
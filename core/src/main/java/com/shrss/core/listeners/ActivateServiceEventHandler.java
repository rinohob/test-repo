package com.shrss.core.listeners;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import com.day.cq.replication.ReplicationAction;
import com.shrss.core.services.TagsPathMappingConfigService;


/**
 * The Class ActivateServiceEventHandler.
 */
@Component(service = EventHandler.class,
immediate = true,
property = {
        Constants.SERVICE_DESCRIPTION + "=Activate path list",
        EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC
}
)
public class ActivateServiceEventHandler implements EventHandler {
	
	private static final String PATH_TAGS_MAPPING = "/etc/acs-commons/lists/path-tags-mapping" ;

	/** The config service. */
	@Reference
	TagsPathMappingConfigService configService;

	/**
	 * Handle event.
	 *
	 * @param event the event
	 */
	@Override
	public void handleEvent(Event event) {
		if(PATH_TAGS_MAPPING.equalsIgnoreCase(ReplicationAction.fromEvent(event).getPath())) {
			configService.getTagsPathMapping();
		}
	}
}

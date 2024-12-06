package com.shrss.core.schedulers;

import com.adobe.cq.dam.cfm.ContentFragment;
import com.adobe.cq.dam.cfm.ContentFragmentException;
import com.adobe.cq.dam.cfm.ContentFragmentManager;
import com.adobe.cq.dam.cfm.FragmentTemplate;
import com.adobe.granite.asset.api.Asset;
import com.adobe.granite.asset.api.AssetManager;
import com.adobe.granite.asset.api.RenditionHandler;
import com.adobe.granite.maintenance.MaintenanceConstants;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.exec.filter.WorkItemFilter;
import com.adobe.granite.workflow.model.WorkflowModel;
import com.adobe.granite.workflow.model.WorkflowNode;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.DamConstants;
import com.google.gson.Gson;

import com.shrss.core.services.unityapi.APIInvoker;
import com.shrss.core.services.unityapi.CIAMService;
import com.shrss.core.services.unityapi.HttpClientFactory;
import com.shrss.core.services.unityapi.pojo.CIAMTokenResponse;
import com.shrss.core.services.unityapi.pojo.location.Locations;

import org.apache.commons.lang3.StringUtils;
import org.apache.jackrabbit.core.fs.FileSystem;
import org.apache.sling.api.resource.*;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.JobManager;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component(service = JobConsumer.class,immediate = true,
        property = { JobConsumer.PROPERTY_TOPICS + "=" + LocationExportScheduler.EXPORT_LOCATIONS_DATA_JOB_TOPIC })

public class LocationsDataExportJobConsumer implements JobConsumer {

    private final Logger log = LoggerFactory.getLogger(LocationsDataExportJobConsumer.class);


    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private JobManager jobManager;
    
    @Reference
    private HttpClientFactory httpClientFactory;

    
    @Reference    
    private APIInvoker shrssAPIInvoker;
    
    @Reference
    private ContentFragmentManager fragmentManager;

    @Override
    public JobResult process(Job job) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.SUBSERVICE, "readService");   
        try (ResourceResolver resolver = resolverFactory.getResourceResolver(param)){
    		if(null !=shrssAPIInvoker) {
    			String locationResponse  = shrssAPIInvoker.invokeUnityLocationAPI();
    			Gson gson = new Gson();
    			Locations[] locations = gson.fromJson(locationResponse, Locations[].class);
    			createContentFragment(resolver, locations);
    		}
        } catch ( PersistenceException e) {
             log.error("Exception while exporting locations data {}", e);
             return JobResult.FAILED;
        } catch (LoginException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ContentFragmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return JobResult.OK;
        }
    
    
    private void createContentFragment(ResourceResolver resolver, Locations[] locations) throws PersistenceException, ContentFragmentException {
    	for(Locations location : locations) {
    	//	ContentFragment myFragment = fragmentManager.c(resolver.getResource("/content/dam/shrss/locations"), resolver.getResource(""), location.getLocationId()+"", location.getLocationId()+"");
    		Resource template = resolver.getResource("/shrss/settings/dam/cfm/models/locations");
    		template.adaptTo(FragmentTemplate.class).createFragment(resolver.getResource("/content/dam/shrss/locations"), location.getLocationId()+"", location.getLocationId()+"");
    		
    	//	httpClientFactory.getExecutor().execute(httpClientFactory.post(myFragment.get, null));
			/*
			 * if (contentFragmentResource != null) { ModifiableValueMap properties =
			 * contentFragmentResource.adaptTo(ModifiableValueMap.class);
			 * properties.put("location", title); properties.put("jcr:description",
			 * content); }
			 */
    	}
    }

}
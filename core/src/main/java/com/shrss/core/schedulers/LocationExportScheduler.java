package com.shrss.core.schedulers;


import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.event.jobs.JobBuilder;
import org.apache.sling.event.jobs.JobManager;
import org.apache.sling.event.jobs.ScheduledJobInfo;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collection;
import java.util.Map;

@Component(immediate = true,configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = LocationExportScheduler.Config.class)
public class LocationExportScheduler {

    private static final Logger log = LoggerFactory.getLogger(LocationExportScheduler.class);

    protected static final String EXPORT_LOCATIONS_DATA_JOB_TOPIC = "com/shrss/jobs/topic/exportlocations";

    private static final String DEFAULT_CRON_VALUE ="0 0 7 1 * ?";

    @ObjectClassDefinition(name = "Locations Data Exporter")
    public @interface Config {

        @AttributeDefinition(name = "File path", description = "Export locations to this DAM location")
        String filePath() default "/content/dam/shrss/locations";

        // Note: the following method(s) need an underscore. They define config values needed by the scheduler, which expects
        // dots in its config properties. To get dots in the property name, we need underscores in the Java code.
        @AttributeDefinition(name = "Schedule", description = "A cron expression defining when this task should run.")
        String scheduler_expression() default LocationExportScheduler.DEFAULT_CRON_VALUE;
    }

    @Reference
    private JobManager jobManager;

    @Activate
    @Modified
    protected void activate(final Map<String, Object> config) {
        log.debug("Activated LocationExportScheduler");
        removeScheduler();
        startScheduledJob(config);
    }

    @Deactivate
    protected void deactivate(final Map<String, String> config) {
        removeScheduler();
    }

    /**
     * Remove a scheduler based on the scheduler ID
     */
    private void removeScheduler() {
        log.debug("Removing LocationExportScheduler");

        Collection<ScheduledJobInfo> scheduledJobInfos = jobManager.getScheduledJobs( EXPORT_LOCATIONS_DATA_JOB_TOPIC, 0, null );
        for(ScheduledJobInfo scheduledJobInfo : scheduledJobInfos ){
            log.debug( "Unscheduling Job {}", scheduledJobInfo.getJobProperties().toString() );
            scheduledJobInfo.unschedule();
        }
    }

    private void startScheduledJob( final Map<String, Object> config){
        JobBuilder jobBuilder = jobManager.createJob(EXPORT_LOCATIONS_DATA_JOB_TOPIC).properties(config);
        JobBuilder.ScheduleBuilder scheduleBuilder = jobBuilder.schedule();
        String cron = PropertiesUtil.toString(config.get("scheduler.expression"), DEFAULT_CRON_VALUE);
        scheduleBuilder.cron( cron );
        ScheduledJobInfo scheduledJobInfo = scheduleBuilder.add();
        if(scheduledJobInfo == null){
            log.error( "Failed to add Scheduled Job {}", EXPORT_LOCATIONS_DATA_JOB_TOPIC );
        } else {
            log.info("Scheduler Job added to the Queue {}", EXPORT_LOCATIONS_DATA_JOB_TOPIC);
        }
    }

}
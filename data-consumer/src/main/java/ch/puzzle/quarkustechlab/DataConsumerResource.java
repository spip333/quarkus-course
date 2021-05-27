package ch.puzzle.quarkustechlab;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/data")
public class DataConsumerResource {

    private static Logger logger = Logger.getLogger(DataConsumerResource.class.getName());

    @Inject
    HealthService healthService;

    @RestClient
    DataProducerService dataProducerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 10)
    public SensorMeasurement getData() {

        logger.info("getData()");

        healthService.registerMessageFetch();

        SensorMeasurement sm =  dataProducerService.getSensorMeasurement();
        return sm;
    }
}

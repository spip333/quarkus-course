package ch.puzzle.quarkustechlab;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/data")
@RegisterRestClient(configKey = "data-producer-api")
public interface DataProducerService {

    @GET
    @Timeout(500)
    @Produces(MediaType.APPLICATION_JSON)
    @Fallback(fallbackMethod = "getDefaultMeasurement")
    SensorMeasurement getSensorMeasurement();

    default SensorMeasurement getDefaultMeasurement() {
        SensorMeasurement sm = new SensorMeasurement();
        sm.data = 3.333;

        return sm;
    }
}

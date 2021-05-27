package ch.puzzle.quarkustechlab;

import java.util.Random;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/data")
public class DataResource {

    Random random = new Random();
    private static Logger logger = Logger.getLogger(DataResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SensorMeasurement getSensorMeasurement() throws InterruptedException {

        logger.info("getSensorMeasurement called!");
/*
        if (random.nextBoolean()) {
            logger.severe("Failed!");
            throw new RuntimeException();
        }
*/
        Thread.sleep(random.nextInt(1000));
        return new SensorMeasurement();
    }
}

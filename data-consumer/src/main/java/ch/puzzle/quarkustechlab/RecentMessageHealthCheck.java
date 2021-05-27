package ch.puzzle.quarkustechlab;

import java.time.Instant;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class RecentMessageHealthCheck implements HealthCheck {

    @Inject
    HealthService healthService;

    @Override
    public HealthCheckResponse call() {
        Instant lastMessageTime = healthService.getLastMessageTime();

        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Last message check")
                .state(lastMessageTime == null || ((lastMessageTime.toEpochMilli() + 60000) >= Instant.now().toEpochMilli()));

        if(lastMessageTime != null) {
            responseBuilder.withData("lastMessageTime", lastMessageTime.toEpochMilli())
                    .withData("ageInMs", (Instant.now().toEpochMilli() - lastMessageTime.toEpochMilli()));
        }

        return responseBuilder.build();
    }
}

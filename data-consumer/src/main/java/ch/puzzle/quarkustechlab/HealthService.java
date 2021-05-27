package ch.puzzle.quarkustechlab;

import java.time.Instant;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HealthService {

    Instant lastMessageTime;

    public void registerMessageFetch() {
        this.lastMessageTime = Instant.now();
    }

    public Instant getLastMessageTime() {
        return lastMessageTime;
    }
}

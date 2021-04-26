package spring.microservices.sample.config.web;

import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Service to get the port on which the application server runs when the default port (0) is set.
 *
 * @author Miguel A. Vila
 */
@Service
public class ServerPortService {
    private int port;

    public int getPort() {
        return port;
    }

    @EventListener
    public void onApplicationEvent(final ServletWebServerInitializedEvent event) {
        port = event.getWebServer().getPort();
    }

}

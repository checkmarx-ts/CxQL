import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
 
@Component
public class UrlBuilder {
 
    private final String host;
    private final String port;
    private final String protocol;
 
    @Autowired
    public UrlBuilder(@Value("${app.server.protocol}") String protocol,
                         @Value("${app.server.host}") String serverHost,
                         @Value("${app.server.port}") int serverPort) {
        this.protocol = protocol.toLowercase();
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }
}
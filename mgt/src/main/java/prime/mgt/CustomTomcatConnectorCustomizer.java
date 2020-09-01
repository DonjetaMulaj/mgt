package prime.mgt;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * CustomTomcatConnectorCustomizer
 */
public class CustomTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
	@Override
	public void customize(Connector connector) {
		connector.setProperty("compression", "on");
		//connector.setProperty("compressableMimeType", "text/html,text/xml,text/plain,application/json,application/xml");
		connector.setProperty("compressableMimeType", "application/json"); //compress JSON responses, makes a difference in reports
	}
}

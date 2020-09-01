package prime.mgt;

import org.apache.catalina.Context;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;

public class CustomTomcatContextCustomizer implements TomcatContextCustomizer {
	@Override
	public void customize(Context context) {
		context.setCookies(true);
		context.setUseHttpOnly(true);
	}
}
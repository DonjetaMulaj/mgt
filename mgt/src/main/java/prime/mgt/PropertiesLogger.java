package prime.mgt;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * @author Isah Bllaca <isah.bllaca@asseco-see.com>
 *
 */
@Configuration
public class PropertiesLogger {
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final Logger logger = LogManager.getLogger(PropertiesLogger.class);
	@Autowired
	private AbstractEnvironment environment;
	
	@PostConstruct
	public void printProperties() {
		StringBuilder sb = new StringBuilder("Property Sources").append(LINE_SEPARATOR);
		for (PropertiesPropertySource p : findPropertiesPropertySources()) {
			sb.append((p.toString())).append(LINE_SEPARATOR);
			for (String propertyKey : p.getPropertyNames()) {
				sb.append("     " + propertyKey + "=" + getPropertyValue(propertyKey));
				sb.append(LINE_SEPARATOR);
			}
		}
		logger.debug(sb);
	}

	private String getPropertyValue(String propertyKey) {
		if (propertyKey.indexOf(".password") != -1 || propertyKey.indexOf(".secret") != -1 || propertyKey.indexOf(".jwtsecret") != -1) {
			return "******";
		}
		return environment.getProperty(propertyKey);
	}

	private List<PropertiesPropertySource> findPropertiesPropertySources() {
		List<PropertiesPropertySource> propertiesPropertySources = new LinkedList<>();
		for (PropertySource<?> propertySource : environment.getPropertySources()) {
			if (propertySource instanceof PropertiesPropertySource) {
				propertiesPropertySources.add((PropertiesPropertySource) propertySource);
			}
		}
		return propertiesPropertySources;
	}
}
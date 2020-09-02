package prime.mgt;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {
	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {}

	

	
}

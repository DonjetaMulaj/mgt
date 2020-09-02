package prime.mgt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ImportResource("classpath:action-role-mapping.xml")
public class SpringBootXmlApplication implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		
	}}

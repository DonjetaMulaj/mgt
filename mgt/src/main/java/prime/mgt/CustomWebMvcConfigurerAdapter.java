package prime.mgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
	 @Autowired
	 LocaleChangeInterceptor localeChangeInterceptor;

	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
	  registry.addInterceptor(localeChangeInterceptor);
	 }
	}

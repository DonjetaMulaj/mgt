package prime.mgt.api.util;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class DateUtils {
	
	
	public Date getNow() {
		return new Date();
	}
}

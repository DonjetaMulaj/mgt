package prime.mgt.api.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.ApiErrorCode;
import prime.mgt.api.exception.ApiException;

@Component
public class ApiUtil {
	public void validatePostRequest(HttpServletRequest req) throws ApiException {
		String queryString = req.getQueryString();
		if (StringUtils.isNotBlank(queryString)) {
			throw new ApiException(ApiErrorCode.ERR0001);
		}
	}
}

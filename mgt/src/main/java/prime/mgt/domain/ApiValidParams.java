package prime.mgt.domain;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import prime.mgt.api.enums.ApiRequestParameter;
@Component
public class ApiValidParams {
	public static List<ApiRequestParameter> apiReqParams;

	public static List<ApiRequestParameter> getApiReqParams() {
		apiReqParams = Arrays.asList(ApiRequestParameter.ACTION, ApiRequestParameter.EMAIL, ApiRequestParameter.USERNAME,ApiRequestParameter.PASSWORD);
		return apiReqParams;
	}
}

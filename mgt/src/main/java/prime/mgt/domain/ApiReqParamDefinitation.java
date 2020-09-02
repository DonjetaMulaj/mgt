package prime.mgt.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import prime.mgt.api.enums.ApiAction;
import prime.mgt.api.enums.ApiRequestParameter;
@Component
public class ApiReqParamDefinitation{
	
	public static Map<ApiAction, List<ApiRequestParameter>> apiReqParamsDefinitation;

	public static Map<ApiAction, List<ApiRequestParameter>> getApiReqParamsDefinitation() {
		apiReqParamsDefinitation = new HashMap<ApiAction, List<ApiRequestParameter>>();
		//Api Params for ApiAuthenticate
		apiReqParamsDefinitation.put(ApiAction.APIAUTHENTICATE, Arrays.asList(ApiRequestParameter.ACTION, ApiRequestParameter.EMAIL,ApiRequestParameter.USERNAME, ApiRequestParameter.PASSWORD));
		//Api Params for ApiQueryProject
		apiReqParamsDefinitation.put(ApiAction.APIQUERYPROJECT, Arrays.asList(ApiRequestParameter.ACTION, ApiRequestParameter.EMAIL,ApiRequestParameter.USERNAME, ApiRequestParameter.PASSWORD));
		return apiReqParamsDefinitation;
	}
	
	
}

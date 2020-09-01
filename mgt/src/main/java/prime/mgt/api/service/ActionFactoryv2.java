package prime.mgt.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.ApiAction;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
@Component
public class ActionFactoryv2 {
	@Autowired
	private ApiAuthenticateService apiAuthenticateService;
	@Autowired
	private ApiQueryProjectService apiQueryProjectService;
	@Autowired
	private ApiSearchProjectService apiSearchProjectService;

	public ApiService getAction(ApiAction action) {
		switch (action) {
		case APIAUTHENTICATE:
			return apiAuthenticateService;
		case APIQUERYPROJECT:
			return apiQueryProjectService;
		case APISEARCHPROJECT:
			return apiSearchProjectService;
		default:
			throw new IllegalArgumentException("Action " + action + " not supported yet on Api Version");
		}
	}
}

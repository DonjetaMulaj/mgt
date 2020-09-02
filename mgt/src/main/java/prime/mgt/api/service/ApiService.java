package prime.mgt.api.service;
import javax.servlet.http.HttpServletRequest;

import prime.mgt.api.exception.ApiException;
import prime.mgt.api.requestholders.RequestHolder;
import prime.mgt.api.sdk.ApiServiceVO;

/**
 * 
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 *
 */
public abstract class ApiService {
	
	/**
	 * Concrete action implementation
	 *
	 * @param requestHolder
	 * @return
	 * @throws ApiException
	 */
	public abstract ApiServiceVO doAction(RequestHolder requestHolder, HttpServletRequest request) throws ApiException;
}

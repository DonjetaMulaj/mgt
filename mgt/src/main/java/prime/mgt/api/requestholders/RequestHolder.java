package prime.mgt.api.requestholders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import prime.mgt.api.enums.ApiAction;
import prime.mgt.api.enums.ApiRequestParameter;
import prime.mgt.domain.ApiValidParams;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
public class RequestHolder {
	private HttpServletRequest request;
	private Map<String, String> validatedParams = new HashMap<>();
	@Autowired
	private ApiValidParams apiValidParams;

	public RequestHolder(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map<String, String> getValidatedParams() {
		return validatedParams;
	}

	public void setValidatedParams(Map<String, String> validatedParams) {
		this.validatedParams = validatedParams;
	}

	public String getEmail() {
		return getParameter(ApiRequestParameter.EMAIL);
	}

	public String getUserName() {
		return getParameter(ApiRequestParameter.USERNAME);
	}

	public String getPassword() {
		return getParameter(ApiRequestParameter.PASSWORD);
	}

	public String getProjectType() {
		return getParameter(ApiRequestParameter.PROJECTTYPE);
	}

	public String getCustomerSatisfaction() {
		return getParameter(ApiRequestParameter.CUSTOMERSATISFACTION);
	}

	public String getStock() {
		return getParameter(ApiRequestParameter.STOCK);
	}

	public String getSales() {
		return getParameter(ApiRequestParameter.SALES);
	}

	public String getPromotions() {
		return getParameter(ApiRequestParameter.PROMOTIONS);
	}

	private String getParameter(ApiRequestParameter param) {
		@SuppressWarnings("static-access")
		List<ApiRequestParameter> validParams = apiValidParams.getApiReqParams();
		if (validParams.contains(param)) {
			return param.name();
		}
		return null;
	}

	public String getAction() {
		return getParameter(ApiRequestParameter.ACTION);
	}

	public String getFilters() {
		return getParameter(ApiRequestParameter.FILTERS);
	}
}

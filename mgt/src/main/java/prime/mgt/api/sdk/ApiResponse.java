package prime.mgt.api.sdk;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import prime.mgt.api.enums.ApiResponseParameter;

public class ApiResponse {
	private Map<String, String> map;
	private String apiErrorCauser;
	private static final Logger logger = LogManager.getLogger(ApiResponse.class);

	public ApiResponse() {
		map = new LinkedHashMap<String, String>(10);
	}

	public String add(String paramName, String value) {
		return map.put(paramName, value);
	}

	public String add(ApiResponseParameter param, String value) {
		return map.put(param.name(), value);
	}

	public Map<String, String> getMap() {
		return map;
	}

	/**
	 * Output as response
	 * 
	 * @return output
	 */
	public String getOutput() {
		StringBuilder buffer = new StringBuilder();
		try {
			if (map != null && map.size() > 0) {
				for (Iterator<String> e = map.keySet().iterator(); e.hasNext();) {
					String key = e.next();
					buffer.append("&");
					buffer.append(URLEncoder.encode(key, "UTF-8"));
					buffer.append("=");
					buffer.append(URLEncoder.encode("" + map.get(key), "UTF-8"));
				}
				buffer.deleteCharAt(0);
			}
		} catch (UnsupportedEncodingException e1) {
			logger.error("ApiResponse -> getOutput()", e1);
		}
		return buffer.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(map).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof ApiResponse) {
			final ApiResponse other = (ApiResponse) obj;
			return new EqualsBuilder().append(map, other.map).isEquals();
		}
		return false;
	}

	public String getApiErrorCauser() {
		return apiErrorCauser;
	}

	public void setApiErrorCauser(String apiErrorDescription) {
		this.apiErrorCauser = apiErrorDescription;
	}

	@Override
	public String toString() {
		return "ApiResponse [map=" + map + ", apiErrorCauser=" + apiErrorCauser + "]";
	}
}

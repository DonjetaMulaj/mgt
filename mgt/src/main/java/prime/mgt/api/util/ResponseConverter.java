package prime.mgt.api.util;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.enums.Responseformat;
import prime.mgt.api.sdk.ApiServiceVO;
import prime.mgt.api.util.converter.JsonConverter;

/**
 * ResponseConverter
 * 
 * @author Erhun Baycelik <Erhun.Baycelik@asseco-see.com.tr>
 * @author Anil Tangul <anil.tangul@asseco-see.com.tr>
 * @author Isah Bllaca <isah.bllaca@asseco-see.com>
 */
@Component
public class ResponseConverter {
	@Autowired
	private JsonConverter jsonConverter;
	private boolean prettyPrint = true;

	@PostConstruct
	public void postConstruct() {
		jsonConverter.setPrettyPrinting(prettyPrint);
	}

	public String convert(ApiServiceVO asvo, Responseformat responseFormat) {
		return jsonConverter.write(asvo);
	}

	public String convertMap(Map<String, String> map, Responseformat responseFormat) {
		return jsonConverter.writeMap(map);
	}

	public void setJsonConverter(JsonConverter jsonConverter) {
		this.jsonConverter = jsonConverter;
	}
}

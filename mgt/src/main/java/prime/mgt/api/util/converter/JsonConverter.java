package prime.mgt.api.util.converter;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import prime.mgt.api.sdk.ApiServiceVO;

/**
 * 
 * @author Donjeta Mulaj<donjeta.mulaj@gmail.com>
 *
 */
@Component
public class JsonConverter extends Converter {
	
	public static final Logger logger = LogManager.getLogger(JsonConverter.class);
	
	@Override
	public String write(ApiServiceVO apiResponse) {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		if (prettyPrinting) {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		mapper.disable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
		try {
			json = mapper.writeValueAsString(apiResponse);
		} catch (JsonProcessingException e) {
			logger.error("Error while trying to process Json", e);
		}
		return json;
	}
	
	@Override
	public String writeAny(Object object) {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		if (prettyPrinting) {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		mapper.disable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("Error while trying to process Json", e);
		}
		return json;
	}
	
	@Override
	public String writeMap(Map<String, String> map) {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		if (prettyPrinting) {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		mapper.disable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
		try {
			json = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			logger.error("Error while trying to process Json", e);
		}
		return json;
	}

}

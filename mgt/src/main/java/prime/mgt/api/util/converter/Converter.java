package prime.mgt.api.util.converter;

import java.util.Map;

import prime.mgt.api.sdk.ApiServiceVO;

/**
 * 
 * @author Donjeta Mulaj<donjeta.mulaj@gmail.com>
 *
 */
public abstract class Converter {
    protected boolean prettyPrinting;

    public abstract String write(ApiServiceVO apiResponse);
    
    public Converter setPrettyPrinting(boolean prettyPrinting) {
        this.prettyPrinting = prettyPrinting;
        return this;
    }

    public abstract String writeMap(Map<String, String> map);
    
    public abstract String writeAny(Object object);
}

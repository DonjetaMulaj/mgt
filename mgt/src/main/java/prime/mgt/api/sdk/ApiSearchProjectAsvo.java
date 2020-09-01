package prime.mgt.api.sdk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**
 * 
 * @author Donjeta Mulaj<donjeta.mulaj@gmail.com>
 *
 */
@JsonInclude(Include.NON_NULL)
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiSearchProjectAsvo extends ApiServiceVO{
	
	
}

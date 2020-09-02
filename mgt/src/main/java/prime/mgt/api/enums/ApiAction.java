package prime.mgt.api.enums;

/**
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 */
public enum ApiAction implements AbstractAction {
	APIAUTHENTICATE, APIQUERYPROJECT, APISEARCHPROJECT;
	
	
	
	public static ApiAction fromString(String input) {
		for (ApiAction s : values()) {
			if (s.name().equals(input))
				return s;
		}
		return null;
	}
}

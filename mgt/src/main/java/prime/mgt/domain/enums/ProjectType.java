package prime.mgt.domain.enums;

/**
 * @author Donjeta
 */
public enum ProjectType {
	MARKETING, RETAIL;
	
	public static ProjectType fromString(String input){
		for(ProjectType s: values()){
			if(s.name().equals(input))
				return s;
		}
		return null;
	}
}

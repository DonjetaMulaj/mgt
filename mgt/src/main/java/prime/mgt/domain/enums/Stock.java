package prime.mgt.domain.enums;

public enum Stock {
	STOCKPERREGION;
	
	public static Stock fromString(String input){
		for(Stock s: values()){
			if(s.name().equals(input))
				return s;
		}
		return null;
	}
}

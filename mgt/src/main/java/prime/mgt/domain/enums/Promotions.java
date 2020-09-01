package prime.mgt.domain.enums;

public enum Promotions {
	CAMPAIGNS("THEMES");
	private String promoType;

	private Promotions(String promoType) {
		this.promoType = promoType;
	}

	public String getPromoType() {
		return promoType;
	}

	public void setPromoType(String promoType) {
		this.promoType = promoType;
	}

	public static Promotions fromString(String input) {
		for (Promotions s : values()) {
			if (s.name().equals(input))
				return s;
		}
		return null;
	}
}

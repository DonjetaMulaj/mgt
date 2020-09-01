package prime.mgt.service.dto;

import prime.mgt.domain.enums.Promotions;

public class RetailDto {
	private Promotions promotions;

	public RetailDto() {
		super();
	}

	public Promotions getPromotions() {
		return promotions;
	}

	public void setPromotions(Promotions promotions) {
		this.promotions = promotions;
	}

	public static class Builder {
		private Promotions promotions;

		public Builder promotions(Promotions promotions) {
			this.promotions = promotions;
			return this;
		}

		public RetailDto build() {
			return new RetailDto(this);
		}
	}

	private RetailDto(Builder builder) {
		this.promotions = builder.promotions;
	}
}

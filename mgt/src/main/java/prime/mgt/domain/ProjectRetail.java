package prime.mgt.domain;

import prime.mgt.domain.enums.Promotions;

/**
 * @author Donjeta Mulaj<donjeta.mulaj@gmail.com>
 */
public class ProjectRetail {
	private Promotions promotions;

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

		public ProjectRetail build() {
			return new ProjectRetail(this);
		}
	}

	private ProjectRetail(Builder builder) {
		this.promotions = builder.promotions;
	}
}

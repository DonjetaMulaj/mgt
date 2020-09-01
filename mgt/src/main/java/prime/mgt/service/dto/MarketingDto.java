package prime.mgt.service.dto;

import prime.mgt.domain.enums.Stock;

public class MarketingDto {
	private String customerSatisfaction;
	private Stock stock;
	private String sales;

	public String getCustomerSatisfaction() {
		return customerSatisfaction;
	}

	public void setCustomerSatisfaction(String customerSatisfaction) {
		this.customerSatisfaction = customerSatisfaction;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public static class Builder {
		private String customerSatisfaction;
		private Stock stock;
		private String sales;

		public Builder customerSatisfaction(String customerSatisfaction) {
			this.customerSatisfaction = customerSatisfaction;
			return this;
		}

		public Builder stock(Stock stock) {
			this.stock = stock;
			return this;
		}

		public Builder sales(String sales) {
			this.sales = sales;
			return this;
		}

		public MarketingDto build() {
			return new MarketingDto(this);
		}
	}

	private MarketingDto(Builder builder) {
		this.customerSatisfaction = builder.customerSatisfaction;
		this.stock = builder.stock;
		this.sales = builder.sales;
	}
}

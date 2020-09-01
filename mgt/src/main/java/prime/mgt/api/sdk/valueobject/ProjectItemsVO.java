package prime.mgt.api.sdk.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import prime.mgt.domain.enums.ItemStatus;
import prime.mgt.domain.enums.ItemType;

@JsonInclude(Include.NON_NULL)
public class ProjectItemsVO {
	private ItemType itemType;
	private String title;
	private UserVO assigneeId;
	private UserVO reporterId;
	private ItemStatus itemStatus;

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UserVO getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(UserVO assigneeId) {
		this.assigneeId = assigneeId;
	}

	public UserVO getReporterId() {
		return reporterId;
	}

	public void setReporterId(UserVO reporterId) {
		this.reporterId = reporterId;
	}

	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}

	public static class Builder {
		private ItemType itemType;
		private String title;
		private UserVO assigneeId;
		private UserVO reporterId;
		private ItemStatus itemStatus;

		public Builder itemType(ItemType itemType) {
			this.itemType = itemType;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder assigneeId(UserVO assigneeId) {
			this.assigneeId = assigneeId;
			return this;
		}

		public Builder reporterId(UserVO reporterId) {
			this.reporterId = reporterId;
			return this;
		}

		public Builder itemStatus(ItemStatus itemStatus) {
			this.itemStatus = itemStatus;
			return this;
		}

		public ProjectItemsVO build() {
			return new ProjectItemsVO(this);
		}
	}

	private ProjectItemsVO(Builder builder) {
		this.itemType = builder.itemType;;
		this.title = builder.title;
		this.assigneeId = builder.assigneeId;
		this.reporterId = builder.reporterId;
		this.itemStatus = builder.itemStatus;
	}
}

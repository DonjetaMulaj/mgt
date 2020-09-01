package prime.mgt.domain;

import prime.mgt.domain.enums.ItemStatus;
import prime.mgt.domain.enums.ItemType;

public class ProjectItems {
	private ItemType itemType;
	private String title;
	private User assigneeId;
	private User reporterId;
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

	public User getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(User assigneeId) {
		this.assigneeId = assigneeId;
	}

	public User getReporterId() {
		return reporterId;
	}

	public void setReporterId(User reporterId) {
		this.reporterId = reporterId;
	}

	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}
}

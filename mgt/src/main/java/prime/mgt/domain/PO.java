package prime.mgt.domain;

import java.io.Serializable;

public abstract class PO implements Serializable{

	private static final long serialVersionUID = -7281818277696605539L;
	
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}

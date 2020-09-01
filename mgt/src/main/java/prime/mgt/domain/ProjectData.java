package prime.mgt.domain;

import java.util.Set;
/**
 * 
 * @author Donjeta Mulaj <donjeta.mulaj@gmail.com>
 *
 */
public class ProjectData extends PO {
	
	private Set<String> tasks;
	private Set<String> notes;

	public Set<String> getTasks() {
		return tasks;
	}

	public void setTasks(Set<String> tasks) {
		this.tasks = tasks;
	}

	public Set<String> getNotes() {
		return notes;
	}

	public void setNotes(Set<String> notes) {
		this.notes = notes;
	}
}

package at.htl.enginecontrol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TaskList {

	private LinkedList<EngineTask> tasks = new LinkedList<EngineTask>();
	
	public TaskList(){
	}
	
	public int addNewTask(EngineTask task){
		tasks.add(task);
		return tasks.indexOf(task);
	}
	
	public boolean removeTask(int id){
		
		tasks.remove(id);
		
		return false;
	}
	
	public void taskComplete(){
		
	}
	
	
}

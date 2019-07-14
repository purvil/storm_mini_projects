package stormTopology.customGrouping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.storm.generated.GlobalStreamId;
import org.apache.storm.grouping.CustomStreamGrouping;
import org.apache.storm.task.WorkerTopologyContext;

public class customGroupingClass implements CustomStreamGrouping, Serializable {
	private List<Integer> targetTasks;
	
	public void prepare(WorkerTopologyContext context, GlobalStreamId stream, List<Integer> targetTasks) {
		this.targetTasks = targetTasks;	
	}

	public List<Integer> chooseTasks(int taskId, List<Object> values) {
		// TODO Auto-generated method stub
		List<Integer> boltsIds = new ArrayList<Integer>();
		Integer boltNum = Integer.parseInt((values.get(1).toString())) % targetTasks.size();
		boltsIds.add(targetTasks.get(boltNum));
		return boltsIds;
	}	
}

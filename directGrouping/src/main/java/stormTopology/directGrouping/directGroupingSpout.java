package stormTopology.directGrouping;

import java.util.List;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class directGroupingSpout extends BaseRichSpout{
	private SpoutOutputCollector collector;
	private Integer i = 0;
	private List<Integer> boltsIds;
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.boltsIds = context.getComponentTasks("Write-To-File-Bolt");
	}
	
	public void ack(Object msgId) {}
	
	public void fail(Object msgId) {}
	
	public void nextTuple() {
		while (i <= 100) {
			Integer intBucket = (this.i/10);
			this.collector.emitDirect(boltsIds.get(getBoltId(intBucket)), new Values(this.i.toString(), intBucket.toString()));
			this.i = this.i + 1;
		}
	}
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("integer", "bucket"));
	}
	
	public Integer getBoltId(Integer intBucket) {
		return intBucket % boltsIds.size();
	}
}

package stormTopology.helloWorld;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class helloWorldSpout extends BaseRichSpout {
	private SpoutOutputCollector collector;
	private Integer i = 0;
	
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		this.i = 0;
	}
	
	public void nextTuple() {
		// Auto generate series of integers and pass it to Bolt
		this.collector.emit(new Values(this.i)); // emit data to next component
		this.i = this.i + 1;
	}
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("field"));
	}
	
	public void ack(Object msgId) {
		
	}
	
	public void fail(Object msgId) {
		
	}
}

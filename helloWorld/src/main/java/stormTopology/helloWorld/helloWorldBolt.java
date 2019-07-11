package stormTopology.helloWorld;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class helloWorldBolt extends BaseBasicBolt{
	public void execute(Tuple input, BasicOutputCollector collector) {
		collector.emit(new Values(input.getInteger(0)*2)); // from input tuple get the first value
	}
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("field"));
	}
	
	public void cleanup() {
		
	}
}

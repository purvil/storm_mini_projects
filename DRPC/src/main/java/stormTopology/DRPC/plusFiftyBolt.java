package stormTopology.DRPC;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class plusFiftyBolt extends BaseBasicBolt{

	public void execute(Tuple input, BasicOutputCollector collector) {
		Integer ip = Integer.parseInt(input.getString(1));
		Integer op = ip + 50;
		collector.emit(new Values(input.getValue(0), op.toString()));
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("id", "result"));
	}
	
}

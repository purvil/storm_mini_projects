package stormTopology.wordCount;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class wordCountBolt extends BaseBasicBolt{
	Integer id;
	String name;
	Map<String, Integer> counters;
	String fileName;
	
	public void prepare(Map conf, TopologyContext context) {
		this.counters = new HashMap<String, Integer>();
	    this.fileName = context.getThisComponentId();
		this.id = context.getThisTaskId();
		this.fileName = conf.get("dirToWrite").toString() 
				+ "output-"+context.getThisTaskId()
				+"-"+context.getThisComponentId()
				+".txt";
	}
	
	public void execute(Tuple input, BasicOutputCollector collector) {
		String str = input.getString(0);
		if (this.counters.containsKey(str)) {
			Integer c = counters.get(str) + 1;
			counters.put(str, c);
		} else {
			this.counters.put(str, 1);
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}
	
	public void cleanup() {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			for (Map.Entry<String, Integer> entry:counters.entrySet()) {
				writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
			}
			writer.close();
		} catch (Exception e) {
			
		}
	}
}

package stormTopology.wordCount;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class wordNormalizerBolt extends BaseBasicBolt{

	public void execute(Tuple input, BasicOutputCollector collector) {
		String line = input.getString(0);
		String[] words = line.split(" ");
		
		for(String word:words) {
			word = word.trim();
			if (!word.isEmpty()) {
				word = word.toLowerCase();
				collector.emit(new Values(word));
			}
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}

}

package stormTopology.wordCount;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class wordCountSpout extends BaseRichSpout{
	private FileReader fileReader;
	private boolean completed = false;
	private SpoutOutputCollector collector;
	private BufferedReader reader;
	private String str;
	
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		try {
			this.fileReader = new FileReader(conf.get("fileToRead").toString());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error reading file [" + conf.get("fileToRead") + "]");
		}
		this.reader = new BufferedReader(fileReader);
	}

	public void nextTuple() {
		if (!completed) {
			try {
				this.str = reader.readLine();
				if (this.str != null) {
					this.collector.emit(new Values(str));
				} else {
					completed = true;
					fileReader.close();
				}
			} catch (Exception e) {
				throw new RuntimeException("Error Reading tuple", e);
			}
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}
}

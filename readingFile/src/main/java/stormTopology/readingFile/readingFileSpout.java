package stormTopology.readingFile;

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

public class readingFileSpout extends BaseRichSpout{
	
	private SpoutOutputCollector collector;
	private boolean completed = false;
	private FileReader fileReader;
	private String str;
	private BufferedReader reader;
	
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		try {
			this.fileReader = new FileReader(conf.get("fileToRead").toString());
			
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Error file not found [" + conf.get("fileToRead") + "]");
		}
		
		this.collector = collector;
		this.reader = new BufferedReader(fileReader); // Helps to iterate line by line in file
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
				throw new RuntimeException("Error reading tuple", e);
			}
		}
	}
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}
	
	public void ack(Object msgId) {
		
	}
	
	public void fail(Object msgId) {
		
	}
}

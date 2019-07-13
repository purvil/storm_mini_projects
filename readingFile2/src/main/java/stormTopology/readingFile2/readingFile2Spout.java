package stormTopology.readingFile2;

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

public class readingFile2Spout extends BaseRichSpout{
	private FileReader fileReader;
	private SpoutOutputCollector collector;
	private boolean completed = false;
	private BufferedReader reader;
	private String str;
	
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		try {
			this.fileReader = new FileReader(conf.get("fileToRead").toString());
		} catch (FileNotFoundException e){
			throw new RuntimeException("Error Reading File [" + conf.get("fileToRead") + "]");
		}
		
		this.collector = collector;
		this.reader = new BufferedReader(fileReader);
	}
	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("id", "first_name", "last_name", "gender", "email"));
	}
	
	public void nextTuple() {
		if (!completed) {
			try {
				this.str = reader.readLine();
				if (this.str != null) {
					this.collector.emit(new Values(str.split(",")));
				} else {
					this.completed = true;
					fileReader.close();
				}
			} catch (Exception e) {
				
			}
		} 
	}
}

package storm.purvil;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Values;
import org.apache.storm.tuple.Fields;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class FileReaderSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private FileReader fileReader;
    private BufferedReader reader;
    private String str;
    private boolean completed = false;

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        try {
            this.fileReader = new FileReader(conf.get("fileToRead").toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error reading file");
        }

        this.collector = collector;
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
            } catch (Exception e){
                throw  new RuntimeException("Error reading tuple", e);
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

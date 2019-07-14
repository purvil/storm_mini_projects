package stormTopology.wordCount;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class wordCountTopology {

	public static void main(String[] args) throws InterruptedException{
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader", new wordCountSpout());
		builder.setBolt("word-normalizer", new wordNormalizerBolt()).shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new wordCountBolt()).fieldsGrouping("word-normalizer", new Fields("word"));
		
		Config conf = new Config();
		conf.setDebug(true);
		conf.put("fileToRead", "/home/purvil/storm_mini_projects/wordCount/sample");
		conf.put("dirToWrite", "/home/purvil/storm_mini_projects/wordCount/");
		
		LocalCluster cluster = new LocalCluster();
		
		try {
			cluster.submitTopology("WordCounter-Topology", conf, builder.createTopology());
			Thread.sleep(30000);
		} finally {
			cluster.shutdown();
		}
	}

}

package stormTopology.readingFile;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class readingFileTopology {

	public static void main(String[] args) throws InterruptedException {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("File-Reader-Spout", new readingFileSpout());
		builder.setBolt("File-Reader-Bolt", new readingFileBolt()).shuffleGrouping("File-Reader-Spout");
		
		Config conf = new Config();
		conf.setDebug(true);
		conf.put("fileToRead", "/home/purvil/storm_mini_projects/readingFile/sample");
		
		LocalCluster cluster = new LocalCluster();
		try {
			cluster.submitTopology("File-Reader-Topology", conf, builder.createTopology());
			Thread.sleep(1000);
		} finally {
			cluster.shutdown();
		}
	}

}

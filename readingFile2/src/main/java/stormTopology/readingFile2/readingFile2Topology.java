package stormTopology.readingFile2;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class readingFile2Topology {

	public static void main(String[] args) throws InterruptedException{
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("Read-Fields-Spout", new readingFile2Spout());
		builder.setBolt("Filter-Fields-Bolt", new readingFile2Bolt()).shuffleGrouping("Read-Fields-Spout");
		
		Config conf = new Config();
		conf.setDebug(true);
		conf.put("fileToRead", "/home/purvil/storm_mini_projects/readingFile2/sample");
		conf.put("dirToWrite", "/home/purvil/storm_mini_projects/readingFile2/");
		
		LocalCluster cluster = new LocalCluster();
		try {
			cluster.submitTopology("Read-Fields-Topology", conf, builder.createTopology());
			Thread.sleep(10000);
		} finally {
			cluster.shutdown();
		}
	}

}

package stormTopology.directGrouping;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class directGroupingTopology {

	public static void main(String[] args) throws InterruptedException{
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("Integer-Spout", new directGroupingSpout());
		builder.setBolt("Write-To-File-Bolt", new directGroupingBolt(), 2).directGrouping("Integer-Spout");
		
		Config conf = new Config();
		conf.put("dirToWrite", "/home/purvil/storm_mini_projects/directGrouping/");
		conf.setDebug(true);
		
		LocalCluster cluster = new LocalCluster();
		try {
			cluster.submitTopology("Shuffle-Grouping-Topology", conf, builder.createTopology());
			Thread.sleep(10000);
		} finally {
			cluster.shutdown();
		}
	}

}

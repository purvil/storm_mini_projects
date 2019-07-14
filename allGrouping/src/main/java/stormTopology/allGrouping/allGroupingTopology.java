package stormTopology.allGrouping;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class allGroupingTopology {

	public static void main(String[] args) throws InterruptedException{
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("Integer-Spout", new allGroupingSpout());
		builder.setBolt("Write-To-File-Bolt", new allGroupingBolt(), 2).allGrouping("Integer-Spout");
		
		Config conf = new Config();
		conf.put("dirToWrite", "/home/purvil/storm_mini_projects/allGrouping/");
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

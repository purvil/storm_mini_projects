package stormTopology.customGrouping;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class customGroupingTopology {

	public static void main(String[] args) throws InterruptedException{
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("Integer-Spout", new customGroupingSpout());
		builder.setBolt("Write-To-File-Bolt", new customGroupingBolt(), 2).customGrouping("Integer-Spout", new customGroupingClass());
		
		Config conf = new Config();
		conf.put("dirToWrite", "/home/purvil/storm_mini_projects/customGrouping/");
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

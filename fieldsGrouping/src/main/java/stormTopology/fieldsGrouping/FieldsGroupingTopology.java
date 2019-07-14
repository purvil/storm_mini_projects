package stormTopology.fieldsGrouping;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class FieldsGroupingTopology {

	public static void main(String[] args) throws InterruptedException{
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("Integer-Spout", new fieldsGroupingSpout());
		builder.setBolt("Write-To-File-Bolt", new FieldsGroupingBolt(), 2).fieldsGrouping("Integer-Spout", new Fields("bucket"));
		
		Config conf = new Config();
		conf.put("dirToWrite", "/home/purvil/storm_mini_projects/fieldsGrouping/");
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

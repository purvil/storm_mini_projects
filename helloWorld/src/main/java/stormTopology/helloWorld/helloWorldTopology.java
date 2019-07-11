package stormTopology.helloWorld;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class helloWorldTopology {

	public static void main(String[] args) throws InterruptedException {
		// Build topology
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("Hello-World-Spout", new helloWorldSpout());
		builder.setBolt("Hello-World-Bolt", new helloWorldBolt()).shuffleGrouping("Hello-World-Spout");
	
		//Configuration
		Config conf = new Config();
		conf.setDebug(true);
		
		// Submit topology to cluster
		LocalCluster cluster = new LocalCluster();
		
		try {
			cluster.submitTopology("Hello-World-Topology", conf, builder.createTopology());
			Thread.sleep(1000);
		} finally {
			cluster.shutdown();
		}
	}

}

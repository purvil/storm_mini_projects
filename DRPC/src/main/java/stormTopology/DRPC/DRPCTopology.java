package stormTopology.DRPC;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.LocalDRPC;
import org.apache.storm.drpc.LinearDRPCTopologyBuilder;

public class DRPCTopology {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		LinearDRPCTopologyBuilder builder = new LinearDRPCTopologyBuilder("plusFifty"); // plusFifty is name of function which is used by DRPC client.
		builder.addBolt(new plusFiftyBolt(), 3);
		Config conf = new Config();
		
		LocalDRPC drpc = new LocalDRPC();
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("drpc-plusFifty", conf, builder.createLocalTopology(drpc));
		
		for (Integer number: new Integer[] {53,62,70}) {
			System.out.println("Result For" + number + ":" + drpc.execute("plusFifty", number.toString()));
		}
		Thread.sleep(10000);
		cluster.shutdown();
		drpc.shutdown();
	}

}

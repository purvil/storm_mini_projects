package storm.purvil;

import  org.apache.storm.Config;
import  org.apache.storm.LocalCluster;
import  org.apache.storm.topology.TopologyBuilder;

public class TopologyMain {
    public static void main(String[] args) throws InterruptedException {
        // Build topology

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("My-first-spout", new MyFirstSpout());
        builder.setBolt("my-first-bolt", new MyFirstBolt()).shuffleGrouping("My-first-spout");

        //Configuration
        Config conf = new Config();
        conf.setDebug(true);

        LocalCluster cluster = new LocalCluster();
        try {
            cluster.submitTopology("my-first topology", conf, builder.createTopology());
            Thread.sleep(1000);
        } finally {
            cluster.shutdown();
        }
    }
}

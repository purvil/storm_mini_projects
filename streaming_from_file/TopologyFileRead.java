package storm.purvil;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class TopologyFileRead {
    public static void main(String[] args) throws InterruptedException{
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("File-Reader-Spout", new FileReaderSpout());
        builder.setBolt("Simple-Bolt", new simpleBolt()).shuffleGrouping("File-Reader-Spout");

        Config conf = new Config();
        conf.setDebug(true);
        conf.put("fileToRead", "/home/purvil/storm/streaming_from_file/myInput.txt");

        LocalCluster cluster = new LocalCluster();
        try {
            cluster.submitTopology("File-Reader-Topology", conf, builder.createTopology());
            Thread.sleep(1000);
        } finally {
            cluster.shutdown();
        }
    }
}

package net.iocanel.karaf.hazelcast.shell;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.Member;
import org.apache.felix.gogo.commands.Command;

import java.net.InetSocketAddress;

/**
 * @author iocanel
 */
@Command(scope = "hazelcast", name = "list", description = "List all members of the hazelcast cluster")
public class ListCommand extends HazelcastCommandSupport {

    private static final String LIST_FORMAT = "%4s %-20s %5s";

    @Override
    protected Object doExecute() throws Exception {
        if (instance != null && instance.getCluster() != null) {
            Cluster cluster = instance.getCluster();
            if (cluster.getMembers() != null) {
                int count = 0;
                System.out.println(String.format(LIST_FORMAT, "No. ", "Host Name", "Port"));
                for (Member member : cluster.getMembers()) {
                    InetSocketAddress address = member.getInetSocketAddress();
                    System.out.println(String.format(LIST_FORMAT, count++, address.getHostName(), address.getPort()));
                }
            } else  System.err.println("No node found in the cluster!");
        }  else  System.err.println("No node found in the cluster!");
        return null;
    }
}

package net.iocanel.karaf.hazelcast.shell;

import com.hazelcast.core.ITopic;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;

/**
 * @author iocanel
 */
@Command(scope = "hazelcast", name = "echo", description = "Echos a message to all cluster nodes")
public class EchoCommand extends HazelcastCommandSupport {

    @Argument(index=0,name="message", description = "The message to echo", required = true, multiValued = false)
    private String message;

    private ITopic topic;

    @Override
    protected Object doExecute() throws Exception {
        if(topic != null) {
           topic.publish(message);
        } else System.err.println("Echo topic not available");
        return null;
    }

    public ITopic getTopic() {
        return topic;
    }

    public void setTopic(ITopic topic) {
        this.topic = topic;
    }
}

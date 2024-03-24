package net.iocanel.karaf.hazelcast.shell;

import com.hazelcast.core.ITopic;
import com.hazelcast.core.MessageListener;

/**
 * @author iocanel
 */
public class EchoListener implements MessageListener<String> {

    private ITopic topic;

    public void onMessage(String message) {
        System.out.println(message);
    }

    public ITopic getTopic() {
        return topic;
    }

    public void setTopic(ITopic topic) {
        this.topic = topic;
        this.topic.addMessageListener(this);
    }
}

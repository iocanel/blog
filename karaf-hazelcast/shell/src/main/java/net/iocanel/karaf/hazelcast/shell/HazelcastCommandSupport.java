package net.iocanel.karaf.hazelcast.shell;

import com.hazelcast.core.HazelcastInstance;
import org.apache.karaf.shell.console.OsgiCommandSupport;

/**
 * @author iocanel
 */
public abstract class HazelcastCommandSupport extends OsgiCommandSupport {

    protected HazelcastInstance instance;

    public HazelcastInstance getInstance() {
        return instance;
    }

    public void setInstance(HazelcastInstance instance) {
        this.instance = instance;
    }
}

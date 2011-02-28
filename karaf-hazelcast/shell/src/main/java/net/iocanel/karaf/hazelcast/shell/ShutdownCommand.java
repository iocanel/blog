package net.iocanel.karaf.hazelcast.shell;

import com.hazelcast.core.LifecycleService;
import org.apache.felix.gogo.commands.Command;

/**
 * @author iocanel
 */
@Command(scope = "hazelcast", name = "shutdown", description = "Stops the hazelcast instance")
public class ShutdownCommand extends HazelcastCommandSupport {

    @Override
    protected Object doExecute() throws Exception {
        if(instance != null && instance.getLifecycleService() != null) {
          LifecycleService service = instance.getLifecycleService();
          service.pause();
        } else System.err.println("Hazelcast Lifecycle Service not found");
        return null;
    }
}

package org.hibernate;

import org.hibernate.transaction.TransactionManagerLocator;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/*
 *
 * @author iocanel
 */
public class Activator implements BundleActivator {

    public void start(BundleContext bc) throws Exception {
        TransactionManagerLocator.setContext(bc);
    }

    public void stop(BundleContext bc) throws Exception {
		TransactionManagerLocator.getInstance().close();
    }
}

package org.hibernate.transaction;

import javax.transaction.TransactionManager;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 *
 * @author iocanel
 */
public class TransactionManagerLocator {

	private final String lookupFilter = "(objectClass=javax.transaction.TransactionManager)";
	private static BundleContext context;
	private static TransactionManagerLocator instance;
	private ServiceTracker serviceTracker;

	//Constructor
	private TransactionManagerLocator() throws Exception {
		if (context == null) {
			throw new Exception("Bundle Context is null");
		} else {
			serviceTracker = new ServiceTracker(context, context.createFilter(lookupFilter), null);
			serviceTracker.open();
		}
	}

	public static synchronized TransactionManagerLocator getInstance() throws Exception {
		if (instance == null) {
			instance = new TransactionManagerLocator();
		}
		return instance;
	}

	public static void setContext(BundleContext context) {
		TransactionManagerLocator.context = context;
	}

	public TransactionManager getTransactionManager() {
		return (TransactionManager) serviceTracker.getService();
	}

	public void close() {
		serviceTracker.close();
		instance = null;
	}
}

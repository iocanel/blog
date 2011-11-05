package org.hibernate.transaction;

import java.util.Properties;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import org.hibernate.HibernateException;

/**
 *
 * @author iocanel
 */
public class OsgiTransactionManagerLookup implements TransactionManagerLookup {

    public TransactionManager getTransactionManager(Properties props) throws HibernateException {
        try {
            return TransactionManagerLocator.getInstance().getTransactionManager();
        } catch (Exception ex) {
            throw new HibernateException("Failed to lookup transaction manager.", ex);
        }
    }

    public String getUserTransactionName() {

        return "java:comp/UserTransaction";
    }

    public Object getTransactionIdentifier(Transaction transaction) {
        return transaction;
    }
}

package com.example.bankapi.repository;

import com.example.bankapi.model.BankAccount;
import com.example.bankapi.model.Card;
import com.example.bankapi.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class SessionFactoryHelper {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Build a SessionFactory object from session-factory config
            // defined in the hibernate.cfg.xml file. In this file we
            // register the JDBC connection information, connection pool,
            // to hibernate dialect that we used and the mapping to our
            // hbm.xml file for each pojo (plain old java object).
            Configuration config = new Configuration();
            sessionFactory = config
                    .addAnnotatedClass(BankAccount.class)
                    .addAnnotatedClass(Card.class)
                    .addAnnotatedClass(User.class)
                    .configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Error in creating SessionFactory object."
                    + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * A static method for other application to get SessionFactory object
     * initialized in this helper class.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

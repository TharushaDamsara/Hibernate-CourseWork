package edu.ijse.theserenitymentalhealththerapycenter.config;

import edu.ijse.theserenitymentalhealththerapycenter.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration instance;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        try {
            Configuration cfg = new Configuration().configure()
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Theraphist.class)
                    .addAnnotatedClass(TheraphyPorgramme.class)
                    .addAnnotatedClass(TheraphySession.class);

            sessionFactory = cfg.buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to build SessionFactory: " + e.getMessage());
        }
    }

    public static FactoryConfiguration getInstance() {
        if (instance == null) {
            instance = new FactoryConfiguration();
        }
        return instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}

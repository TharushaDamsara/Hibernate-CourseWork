package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.config.FactoryConfiguration;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PaymentDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Payment;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphyPorgramme;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphySession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {
    private final FactoryConfiguration configuration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Payment payment) {
        Transaction transaction = null;
        try (Session session = configuration.getSession();) {
            transaction = session.beginTransaction();
            session.persist(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean update(Payment payment) {
        Transaction transaction = null;
        try (Session session = configuration.getSession();) {
            transaction = session.beginTransaction();
            session.merge(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public List<Payment> getAll() {
        try (Session session = configuration.getSession();) {
            return session.createQuery("from Payment", Payment.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deletebypk(String pk) {
        Transaction transaction = null;
        try (Session session = configuration.getSession();) {
            transaction = session.beginTransaction();
            Payment payment = session.get(Payment.class, pk);
            if (payment != null) {
                session.remove(payment);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();

            }
            return false;
        }
    }

    @Override
    public Optional<String> getLastPK() {

        Session session = null;
        try {
            session = configuration.getSession();
            String lastId = session.createQuery(
                            "SELECT p.id FROM Payment p ORDER BY p.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastId != null) {
                int numericPart = Integer.parseInt(lastId.substring(1)); // skip 'P'
                int nextId = numericPart + 1;
                String newId = String.format("P%03d", nextId); // P001, P002, ...
                return Optional.of(newId);
            } else {
                return Optional.of("P001");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}

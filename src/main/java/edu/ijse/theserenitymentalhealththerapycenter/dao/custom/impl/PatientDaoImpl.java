package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;

import edu.ijse.theserenitymentalhealththerapycenter.config.FactoryConfiguration;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.PatientDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphyPorgramme;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PatientDaoImpl implements PatientDao {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Patient patient) {

        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSession()) {
            transaction = session.beginTransaction();

            session.persist(patient);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Patient patient) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSession()) {
            transaction = session.beginTransaction();
            session.merge(patient);
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Patient> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Patient", Patient.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deletebypk(String pk) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSession()) {
            transaction = session.beginTransaction();

            Patient patient = session.get(Patient.class, pk);
            if (patient != null) {
                session.remove(patient);
            } else {
                return false;
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = null;
        try {
            session = factoryConfiguration.getSession();
            String lastId = session.createQuery(
                            "SELECT p.id FROM Patient p ORDER BY p.id DESC", String.class)
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


    @Override
    public Patient getDetails(String selectedItem) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        Patient patient = session.get(Patient.class, selectedItem);
        session.getTransaction().commit();
        session.close();
        return patient;
    }

    @Override
    public ArrayList<Patient> getAlpatientIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        Query<Patient> query = session.createQuery("FROM Patient", Patient.class);
        List<Patient> results = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return (ArrayList<Patient>) results;
    }
}

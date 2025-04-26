package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.config.FactoryConfiguration;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphyProgrammeDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphyPorgramme;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TheraphyProgrammeDaoImpl implements TheraphyProgrammeDao {
    @Override
    public TheraphyPorgramme getDetails(String selectedItem) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        TheraphyPorgramme therapyProgram = session.get(TheraphyPorgramme.class, selectedItem);
        session.getTransaction().commit();
        session.close();
        return therapyProgram;
    }

    @Override
    public ArrayList<TheraphyPorgramme> getAlprgrammeIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        Query<TheraphyPorgramme> query = session.createQuery("FROM TheraphyPorgramme", TheraphyPorgramme.class);
        List<TheraphyPorgramme> results = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return (ArrayList<TheraphyPorgramme>) results;
    }

    @Override
    public double getFee(String selectedItem) {
        Session session = null;
        Transaction transaction = null;
        TheraphyPorgramme therapyProgramme =null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            therapyProgramme = session.get(TheraphyPorgramme.class, selectedItem);
            transaction.commit();
            return therapyProgramme.getFee();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
         return 0.0;
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean save(TheraphyPorgramme theraphyPorgramme) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()) {
            transaction = session.beginTransaction();
            session.persist(theraphyPorgramme);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(TheraphyPorgramme theraphyPorgramme) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()) {
            transaction = session.beginTransaction();
            session.merge(theraphyPorgramme);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<TheraphyPorgramme> getAll() {
        try (Session session = factoryConfiguration.getSession()) {
            return session.createQuery("from TheraphyPorgramme",TheraphyPorgramme.class).list();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deletebypk(String pk) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()) {
            transaction = session.beginTransaction();
           TheraphyPorgramme theraphyPorgramme = session.get(TheraphyPorgramme.class, pk);
           if (theraphyPorgramme != null) {
               session.remove(theraphyPorgramme);
               transaction.commit();
               return true;
           }
           else {
               return false;
           }
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = null;
        try {
            session = factoryConfiguration.getSession();
            String lastId = session.createQuery(
                            "SELECT p.id FROM TheraphyPorgramme p ORDER BY p.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastId != null) {
                int numericPart = Integer.parseInt(lastId.substring(1));
                int nextId = numericPart + 1;
                String newId = String.format("G%03d", nextId);
                return Optional.of(newId);
            } else {
                return Optional.of("G001");
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

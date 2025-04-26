package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.config.FactoryConfiguration;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphySessionDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Patient;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TheraphySessonDaoImpl implements TheraphySessionDao {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean save(TheraphySession theraphySession) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()){
            transaction = session.beginTransaction();
            session.persist(theraphySession);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public boolean update(TheraphySession theraphySession) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()){
            transaction = session.beginTransaction();
            session.merge(theraphySession);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public List<TheraphySession> getAll() {
        try (Session session = factoryConfiguration.getSession()){
           return session.createQuery("from TheraphySession",TheraphySession.class).list();
        }
        catch (Exception e){
            e.printStackTrace();
          return Collections.emptyList();
        }
    }

    @Override
    public boolean deletebypk(String pk) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()) {
            transaction = session.beginTransaction();
            TheraphySession theraphySession = session.get(TheraphySession.class, pk);
            if (theraphySession != null){
                session.remove(theraphySession);
                transaction.commit();
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e){
            if (transaction != null){
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
            session = factoryConfiguration.getSession();
            String lastId = session.createQuery(
                            "SELECT p.id FROM TheraphySession p ORDER BY p.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();

            if (lastId != null) {
                int numericPart = Integer.parseInt(lastId.substring(1)); // skip 'P'
                int nextId = numericPart + 1;
                String newId = String.format("S%03d", nextId); // P001, P002, ...
                return Optional.of(newId);
            } else {
                return Optional.of("S001");
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
    public TheraphySession getDetails(String selectedItem) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        TheraphySession session1 = session.get(TheraphySession.class, selectedItem);
        session.getTransaction().commit();
        session.close();
        return session1;
    }

    @Override
    public ArrayList<TheraphySession> gettherapyids() {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        Query query = session.createQuery("from TheraphySession", TheraphySession.class);
        List resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return (ArrayList<TheraphySession>) resultList;
    }

    @Override
    public Double getPayment(String selectedItem) {
        if (selectedItem == null || selectedItem.isEmpty()) {
            throw new NullPointerException("selectedItem is null or empty");
        }
        Session session = null;
        Transaction transaction = null;
        TheraphySession theraphySession = null;

        try {
            session = factoryConfiguration.getSession();
            theraphySession = session.get(TheraphySession.class, selectedItem);
            transaction = session.beginTransaction();
            transaction.commit();
            System.out.println(theraphySession.getFee());
        }
        catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }
        finally {
            if (session != null){
                session.close();
            }
        }
        return theraphySession.getFee();
    }

}

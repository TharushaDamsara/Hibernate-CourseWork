package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.config.FactoryConfiguration;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphistDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Theraphist;
import edu.ijse.theserenitymentalhealththerapycenter.entity.TheraphyPorgramme;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TheraphistDaoImpl implements TheraphistDao {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean save(Theraphist theraphist) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()){
            transaction = session.beginTransaction();
            session.persist(theraphist);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Theraphist theraphist) {

        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()){
            transaction = session.beginTransaction();
            session.merge(theraphist);
            transaction.commit();
            return true;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Theraphist> getAll() {
        try (Session session = factoryConfiguration.getSession()){
          return  session.createQuery("from Theraphist",Theraphist.class).list();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public boolean deletebypk(String pk) {
        Transaction transaction = null;
        try (Session session = factoryConfiguration.getSession()){
            transaction = session.beginTransaction();
            Theraphist theraphist = session.get(Theraphist.class, pk);
            if (theraphist != null) {
                session.remove(theraphist);
            }
            else {
                return false;
            }
            transaction.commit();
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
        return Optional.empty();
    }

    @Override
    public Theraphist getDetails(String selectedItem) {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();
        Theraphist theraphist = session.get(Theraphist.class, selectedItem);
        session.getTransaction().commit();
        session.close();
        return theraphist;
    }

    @Override
    public ArrayList<Theraphist> getAltherapistIds() {
        Session session = FactoryConfiguration.getInstance().getSession();
        session.beginTransaction();

        Query<Theraphist> query = session.createQuery("FROM Theraphist", Theraphist.class);
        List<Theraphist> results = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return (ArrayList<Theraphist>) results;
    }
}

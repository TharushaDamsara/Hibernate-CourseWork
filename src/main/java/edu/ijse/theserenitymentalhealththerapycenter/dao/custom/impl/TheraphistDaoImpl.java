package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.config.FactoryConfiguration;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.TheraphistDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.Theraphist;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
}

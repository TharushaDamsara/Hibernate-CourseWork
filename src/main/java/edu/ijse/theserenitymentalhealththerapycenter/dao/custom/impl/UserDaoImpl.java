package edu.ijse.theserenitymentalhealththerapycenter.dao.custom.impl;


import edu.ijse.theserenitymentalhealththerapycenter.config.FactoryConfiguration;
import edu.ijse.theserenitymentalhealththerapycenter.dao.custom.UserDao;
import edu.ijse.theserenitymentalhealththerapycenter.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final FactoryConfiguration factoryConfiguration =FactoryConfiguration.getInstance();
    @Override
    public boolean save(User user) {
        Transaction transaction = null;

        try (Session session = factoryConfiguration.getSession()) {
            transaction = session.beginTransaction();

            session.persist(user);

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
    public boolean update(User user) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean deletebypk(String pk) {
        return false;
    }

    @Override
    public Optional<String> getLastPK() {
        Session session = null;
        try {
            session = factoryConfiguration.getSession();

            // Fetch the last id as String
            String lastPk = session
                    .createQuery("SELECT u.id FROM User u ORDER BY u.id DESC", String.class)
                    .setMaxResults(1)
                    .uniqueResult();

            // If lastPk is not null, generate next id
            if (lastPk != null) {
                // Assuming your ID format is like "U001", "U002"
                int lastNumericPart = Integer.parseInt(lastPk.substring(1)); // remove 'U' and parse number
                int newNumericPart = lastNumericPart + 1;
                String newPk = String.format("U%03d", newNumericPart); // format as U003, U004, etc.

                return Optional.of(newPk);
            } else {
                // If no user yet, start with U001
                return Optional.of("U001");
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
    public boolean cheackUser(String userName) {
        Session session = null;
        try {
            session = factoryConfiguration.getSession();

            String username = session.createQuery("SELECT u.username FROM User u WHERE u.username = :username", String.class)
                    .setParameter("username", userName)
                    .uniqueResult();

            return username != null;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public User cheackPassword(String userName) {
        Session session = null;
        try {
            session = factoryConfiguration.getSession();

            User user = session.createQuery("FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", userName)
                    .uniqueResult();

            System.out.println(user.getPassword());
            System.out.println(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

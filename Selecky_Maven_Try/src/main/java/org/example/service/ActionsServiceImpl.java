package org.example.service;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Arrays;
import java.util.List;

public class ActionsServiceImpl implements ActionService {

    @Override
    public void add(User user, SessionFactory sessionFactory) {
        Session session = openSession(sessionFactory);
        session.beginTransaction();
        session.persist(user);
        closeSession(session);
    }

    @Override
    public void printAll(SessionFactory sessionFactory) {
        Session session = openSession(sessionFactory);
        session.beginTransaction();
        List<Object[]> allUsers = session.createNamedQuery("getAllUsers", Object[].class).getResultList();

        for (Object[] x : allUsers) {
            System.out.println(Arrays.toString(x));
        }
        closeSession(session);
    }

    @Override
    public void deleteAll(SessionFactory sessionFactory) {
        Session session = openSession(sessionFactory);
        session.beginTransaction();
        session.createNamedQuery("deleteAllUsers", Object[].class).executeUpdate();
        closeSession(session);
    }

    private Session openSession(SessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }

    private void closeSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }
}

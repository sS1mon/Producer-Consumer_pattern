package org.example.service;

import org.example.entity.User;
import org.hibernate.SessionFactory;

public interface ActionService {
    public void add(User user, SessionFactory sessionFactory);
    public void printAll(SessionFactory sessionFactory);
    public void deleteAll(SessionFactory sessionFactory);
}

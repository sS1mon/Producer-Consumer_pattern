package org.example.config;

import org.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataConfig {

    public  SessionFactory dbSetup() {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(User.class);
            return configuration.buildSessionFactory();
    }

}

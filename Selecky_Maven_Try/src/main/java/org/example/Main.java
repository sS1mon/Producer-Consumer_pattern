package org.example;

import org.example.config.DataConfig;
import org.example.entity.User;
import org.example.pattern.Consumer;
import org.example.pattern.Producer;
import org.example.service.Action;
import org.example.service.ActionService;
import org.example.service.ActionType;
import org.example.service.ActionsServiceImpl;
import org.hibernate.SessionFactory;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        /*
        Hello,
        program is connected to MySql DB please don't forget to change properties to yours
        in this file  src/main/resources/hibernate.cfg.xml.

        Also if possible please send me some sort feedback (seleckysimo@gmail.com), so I can improve my coding because
        I am a junior developer and I have a long way to go to be good.
         */

        Queue<Action> queue = new LinkedList<>();
        DataConfig dataConfig = new DataConfig();
        SessionFactory sessionFactory = dataConfig.dbSetup();
        ActionService actionService = new ActionsServiceImpl();

        Producer producer = new Producer(queue, actionService);
        Consumer consumer = new Consumer(queue);

        producer.addTask(new Action(ActionType.ADD, sessionFactory, new User(1, "A1", "Martin")));
        producer.addTask(new Action(ActionType.ADD, sessionFactory, new User(2, "A2", "Robert")));
        producer.addTask(new Action(ActionType.PRINTALL, sessionFactory));
        producer.addTask(new Action(ActionType.DELETEALL, sessionFactory));
        producer.addTask(new Action(ActionType.PRINTALL, sessionFactory));

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();

    }
}
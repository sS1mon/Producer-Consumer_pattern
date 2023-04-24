package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.User;
import org.hibernate.SessionFactory;

@Getter
@Setter
@AllArgsConstructor
public class Action {
    private ActionType actionType;
    private SessionFactory sessionFactory;
    private User user;

    public Action(ActionType actionType, SessionFactory sessionFactory) {
        this.actionType = actionType;
        this.sessionFactory = sessionFactory;
    }
}

package org.example.service;

import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class ActionsServiceImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @Mock
    private Query<Object[]> query;

    private  ActionsServiceImpl actionsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        actionsService = new ActionsServiceImpl();
        query = mock(Query.class);

        when(sessionFactory.openSession()).thenReturn(session);
        when(session.beginTransaction()).thenReturn(mock(Transaction.class));
        when(session.getTransaction()).thenReturn(mock(Transaction.class));
        when(session.createNamedQuery(anyString(), eq(Object[].class))).thenReturn(query);
        when(query.executeUpdate()).thenReturn(1);
    }

    @Test
    void addUserToDatabaseOK() {
        User user = new User(1, "A1", "AAA");

        doNothing().when(session).persist(user);
        doNothing().when(session).close();

        actionsService.add(user, sessionFactory);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).persist(user);
        verify(session.getTransaction(), times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    void printAllUsersInDataBaseOK() {
        actionsService.printAll(sessionFactory);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session, times(1)).createNamedQuery(eq("getAllUsers"), eq(Object[].class));
        verify(session.getTransaction(), times(1)).commit();
        verify(session, times(1)).close();
    }

    @Test
    void deleteAllUsersFromDatabaseOK() {
        actionsService.deleteAll(sessionFactory);

        verify(sessionFactory, times(1)).openSession();
        verify(session, times(1)).beginTransaction();
        verify(session,times(1)).createNamedQuery(eq("deleteAllUsers"), eq(Object[].class));
        verify(session.getTransaction(), times(1)).commit();
        verify(session, times(1)).close();
    }
}
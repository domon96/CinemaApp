package dao;

import domain.BaseEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class HibernateUtil {

    private final Session session;
    private final Class aClass;

    public HibernateUtil(EntityManager entityManager, Class aClass) {
        this.session = entityManager.unwrap(Session.class);
        this.aClass = aClass;
    }

    public List findAll() {
        return session.createQuery("From " + aClass.getName(), aClass).getResultList();
    }

    public void printAll() {
        List<Object> entities = findAll();
        for (Object entity : entities) {
            System.out.println(entity);
        }
    }

    public void update() {
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Object findById(int id) {
        Query query = session.createNamedQuery("findById", aClass);
        query.setParameter(1, id);
        return query.getSingleResult();
    }

    public void create(Object objectToCreate) {
        List list = findAll();
        if (list.contains(objectToCreate)) {
            return;
        }
        EntityTransaction transaction = null;
        try {
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            session.persist(objectToCreate);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void close() {
        session.close();
    }
}

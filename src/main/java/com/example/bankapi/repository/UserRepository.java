package com.example.bankapi.repository;

import com.example.bankapi.DTO.UserDTO;
import com.example.bankapi.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepository {
    public User get(long id) {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.load(User.class, id);
        session.getTransaction().commit();
        session.close();

        return user;
    }

    public long save(UserDTO userDTO) {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        User user = new User(userDTO.getFirst_name(),
                userDTO.getLast_name(), userDTO.getNumber());

        session.persist(user);
        session.getTransaction().commit();
        session.close();

        return user.getId();
    }

    public List<User> getAll() {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        CriteriaQuery<User> criteria = session.getCriteriaBuilder()
                .createQuery(User.class);
        criteria.from(User.class);

        List<User> result = session.createQuery(criteria).getResultList();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public User getByNumber(String number) {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        criteria.select(root).where(cb.equal(root.get("number"), number));
        Query<User> query = session.createQuery(criteria);



        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}

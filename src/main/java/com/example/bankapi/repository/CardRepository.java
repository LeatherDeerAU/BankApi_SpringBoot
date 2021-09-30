package com.example.bankapi.repository;

import com.example.bankapi.model.BankAccount;
import com.example.bankapi.model.Card;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CardRepository {


    public Card get(long id) {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        Card cur = session.get(Card.class, id);
        session.getTransaction().commit();
        session.close();

        return cur;
    }

    public Card findByNumber(String number) {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Card> criteria = cb.createQuery(Card.class);
        Root<Card> root = criteria.from(Card.class);

        criteria.select(root).where(cb.equal(root.get("number"), number));
        Query<Card> query = session.createQuery(criteria);



        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            session.getTransaction().commit();
            session.close();
        }

    }

    public long save(String number, long bankAccountId) {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();
        BankAccount account = session.load(BankAccount.class, bankAccountId);
        Card card = new Card(number, account);


        session.persist(card);
        session.getTransaction().commit();
        session.close();
        return card.getId();
    }

    public List<Card> getAll() {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        CriteriaQuery<Card> criteria = session.getCriteriaBuilder()
                .createQuery(Card.class);
        criteria.from(Card.class);

        List<Card> result = session.createQuery(criteria).getResultList();

        session.getTransaction().commit();
        session.close();

        return result;
    }

}

package com.example.bankapi.repository;

import com.example.bankapi.DTO.BankAccount_DTO;
import com.example.bankapi.model.BankAccount;
import com.example.bankapi.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class BankAccountRepository {

    public BankAccount get(long id) {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        BankAccount cur = session.get(BankAccount.class, id);
        session.getTransaction().commit();
        session.close();

        return cur;
    }

    public long save(BankAccount_DTO bankAccount_dto) {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.load(User.class, bankAccount_dto.getUser_id());
        BankAccount bankAccount = new BankAccount(bankAccount_dto.getBalance(), user);

        session.persist(bankAccount);
        session.getTransaction().commit();
        session.close();
        return bankAccount.getId();
    }

    public void update(BankAccount detachedAccount) {
        Session session = SessionFactoryHelper.getSessionFactory().openSession();
        session.beginTransaction();

        session.merge(detachedAccount);
        session.getTransaction().commit();
        session.close();
    }
}

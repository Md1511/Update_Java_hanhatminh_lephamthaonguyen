package com.raven.DAO;

import com.raven.model.Model_User_Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements InterfaceDAO<Model_User_Account> {
    @Override
    public List<Model_User_Account> selectAll() {
        List<Model_User_Account> list =new ArrayList<>();
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();

            String hql ="from Model_User_Account ";
            Query query = session.createQuery(hql);
            list = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                session.close();
        }
        return list;
    }

    public Model_User_Account getUserByUsername(String username) {
        List<Model_User_Account> list = new ArrayList<>();
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();

        try {
            session.beginTransaction();

            String hql = "from Model_User_Account c where c.userName = :Username";
            Query query = session.createQuery(hql);
            query.setParameter("Username", username);
            list = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public boolean saveOrUpdate(Model_User_Account modelUserAccount) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(modelUserAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean insert(Model_User_Account modelRegister) {
        return saveOrUpdate(modelRegister);
    }

    @Override
    public boolean update(Model_User_Account modelRegister) {
        return saveOrUpdate(modelRegister);
    }

    @Override
    public boolean delete(Model_User_Account modelRegister) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(modelRegister);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Model_User_Account selectById(Model_User_Account modelRegister) {
        List<Model_User_Account> list = new ArrayList<>();
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            String hql = "from Model_User_Account c where c.userID = :UserID";
            Query query = session.createQuery(hql);
            query.setParameter("UserID", modelRegister.getUserID());
            list = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list.size()>0) {
            return list.get(0);
        } else {
            return null;
        }
    }

}

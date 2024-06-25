package com.raven.controller;

import com.raven.DAO.UserDAO;
import com.raven.form.Delete;
import com.raven.main.Main;
import com.raven.model.Model_User_Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Action_Delete implements ActionListener {
    private Delete delete;
    private Main main;
    private boolean isExited;

    public Action_Delete(Delete delete) {
        this.delete = delete;
//        this.isExited = false;
    }

    //    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//    Session session = factory.openSession();
    @Override
    public void actionPerformed(ActionEvent e) {
        UserDAO userDAO = new UserDAO();
        String src = e.getActionCommand();
        System.out.println(src);

        if (src.equalsIgnoreCase("CHECK")) {
            String username = delete.user.getText();


            Model_User_Account user = userDAO.getUserByUsername(username);

            if (user != null) {

                delete.exist.setVisible(false);
                delete.enter.setVisible(true);
                System.out.println(user);
            } else {

                delete.exist.setVisible(true);
                delete.enter.setVisible(false);
                System.out.println("Ko ton tai");
            }

        } else if (src.equalsIgnoreCase("ENTER")) {
            String username = delete.user.getText();

            Model_User_Account user = userDAO.getUserByUsername(username);

            userDAO.delete(user);

//------------------------------------------------------------------------------------

        }
    }
}

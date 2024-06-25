package com.raven.controller;

import com.raven.DAO.UserDAO;
import com.raven.form.Update;
import com.raven.main.Main;
import com.raven.model.Model_User_Account;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Action_Update implements ActionListener {
    private Update update;
//    private Main main;

    public Action_Update(Update update) {
        this.update = update;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserDAO userDAO = new UserDAO();
        String src = e.getActionCommand();
        System.out.println(src);

        if (src.equalsIgnoreCase("CHECK")) {
            String username = update.user.getText();


            Model_User_Account user = userDAO.getUserByUsername(username);

            if (user != null) {
                System.out.println(user);

                update.exist.setVisible(false);

                update.enter.setVisible(true);
                update.jLabel3.setVisible(true);
                update.jPasswordField1.setVisible(true);
                update.radio1.setVisible(true);
                update.radio2.setVisible(true);


            } else {

                update.exist.setVisible(true);

                update.enter.setVisible(false);
                update.jLabel3.setVisible(false);
                update.jPasswordField1.setVisible(false);
                update.radio1.setVisible(false);
                update.radio2.setVisible(false);
                System.out.println("Ko ton tai");
            }

        } else if (src.equalsIgnoreCase("ENTER")) {
            String username = update.user.getText();

            Model_User_Account user = userDAO.getUserByUsername(username);

            String encryptedPass = BCrypt.hashpw(update.jPasswordField1.getText(), BCrypt.gensalt());

            user.setPassword(encryptedPass);

            userDAO.saveOrUpdate(user);


//            DefaultTableModel model = (DefaultTableModel) main.jTable1.getModel();
//            model.setRowCount(0);
//
//
//            List<Model_User_Account> rs = userDAO.selectAll();
//            for (Model_User_Account row : rs) {
//                int ID = row.getUserID();
//                String name = String.valueOf(row.getUserName());
//                String pass = String.valueOf(row.getPassword());
//
//                model.addRow(new Object[]{ID, name, pass});
//            }
//            main.jTable1.setModel(model);
//
//            userDAO.delete(user);
////            isExited = true;

        }
        if (src.equalsIgnoreCase("SHOW")) {
            update.jPasswordField1.setEchoChar((char) 0);
        }
        if (src.equalsIgnoreCase("HIDE")) {
            update.jPasswordField1.setEchoChar('*');
        }
    }
}

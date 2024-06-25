package com.raven.controller;

import com.raven.DAO.UserDAO;
import com.raven.form.Delete;
import com.raven.form.ServerUI;
import com.raven.form.Update;
import com.raven.main.Main;
import com.raven.model.Model_User_Account;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Action_main implements ActionListener {
    private Main main;
    private Update update;
    private Delete delete;
    public Action_main(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UserDAO dao = new UserDAO();
        String src = e.getActionCommand();
        System.out.println("M da nhan vao nut " + src);

        if(src.equalsIgnoreCase("UPDATE")) {
            new Update();
        } else if(src.equalsIgnoreCase("DELETE")) {
            new Delete();
        }
        if(src.equalsIgnoreCase("LOAD")) {
            DefaultTableModel model = (DefaultTableModel) main.jTable1.getModel();
            model.setRowCount(0);


            List<Model_User_Account> rs = dao.selectAll();
            for (Model_User_Account row : rs) {
                int ID = row.getUserID();
                String name = String.valueOf(row.getUserName());
                String pass = String.valueOf(row.getPassword());

                model.addRow(new Object[]{ID, name, pass});
            }
            main.jTable1.setModel(model);

        }
        if(src.equalsIgnoreCase("CREATE A VIP ROOM")) {
            new ServerUI();
        }

    }
}

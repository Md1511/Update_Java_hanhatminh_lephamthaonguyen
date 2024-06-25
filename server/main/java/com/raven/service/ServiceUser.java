package com.raven.service;

import com.raven.connection.DatabaseConnection;
import com.raven.model.*;
import org.mindrot.jbcrypt.BCrypt;
//import com.raven.model.Model_Register;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser {

    public ServiceUser() {
        this.con = DatabaseConnection.getInstance().getConnection();
    }

    public Model_Message register(Model_User_Account data) {
        Model_Message message = new Model_Message();
        try {
            PreparedStatement p = con.prepareStatement(CHECK_USER);
            p.setString(1, data.getUserName());
            ResultSet r = p.executeQuery();
            if (r.first()) {
                message.setAction(false);
                message.setMessage("User Already Exists");
            } else {
                message.setAction(true);

                String encryptedPassword = BCrypt.hashpw(data.getPassword(), BCrypt.gensalt());

                con.setAutoCommit(false);
                p = con.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                p.setString(1, data.getUserName());
                p.setString(2, encryptedPassword);
                p.execute();
                r = p.getGeneratedKeys();
                r.first();
                int userID = r.getInt(1);
                r.close();
                p.close();

                con.commit();
                con.setAutoCommit(true);
                message.setAction(true);
                message.setMessage("Ok");
                message.setData(new Model_User_Account(userID, data.getUserName(), true));
            }
            r.close();
            p.close();
        } catch (SQLException e) {
            message.setAction(false);
            message.setMessage("Server Error");
            try {
                if (con.getAutoCommit() == false) {
                    con.rollback();
                    con.setAutoCommit(true);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return message;
    }


    public Model_User_Account login(Model_Login login) throws SQLException {
        Model_User_Account data = null;
        PreparedStatement p = con.prepareStatement(LOGIN);
        p.setString(1, login.getUserName());


        ResultSet r = p.executeQuery();
        if (r.first()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);
            String encryptedPassword = r.getString(3);

            if (BCrypt.checkpw(login.getPassword(), encryptedPassword)) {
                data = new Model_User_Account(userID, userName);
            }
        }

        r.close();
        p.close();
        return data;
    }

    public List<Model_User_Account> getUser(int exitUser) throws SQLException {
        List<Model_User_Account> list = new ArrayList<>();
        PreparedStatement p = con.prepareStatement(SELECT_USER_ACCOUNT);
        p.setInt(1, exitUser);
        ResultSet r = p.executeQuery();
        while (r.next()) {
            int userID = r.getInt(1);
            String userName = r.getString(2);

            list.add(new Model_User_Account(userID, userName, checkUserStatus(userID)));
        }
        r.close();
        p.close();
        return list;
    }

    private boolean checkUserStatus(int userID) {
        List<Model_Client> clients = Service.getInstance(null).getListClient();
        for (Model_Client c : clients) {
            if (c.getUser().getUserID() == userID) {
                return true;
            }
        }
        return false;
    }

//    private boolean checkUserStatus(int userID) {
//        List<Model_Client> clients = Service.getInstance(null).getListClient();
//        Model_ClientStatusManager statusManager = new Model_ClientStatusManager();
//
//        // Lặp qua danh sách các client và thêm clientID vào ClientStatusManager nếu client đó đang trực tuyến
//        for (Model_Client c : clients) {
//            if (statusManager.isClientOnline(c.getUser().getUserID())) {
//                statusManager.addOnlineClient(c.getUser().getUserID());
//            }
//        }
//
//        return statusManager.isClientOnline(userID);
//    }

    //  SQL
//    private final String LOGIN = "select UserID, user_account.UserName from `user` join user_account using (UserID) where `user`.UserName=BINARY(?) and `user`.`Password`=BINARY(?) and user_account.`Status`='1'";
//    private final String SELECT_USER_ACCOUNT = "select UserID, UserName from user where user.`Status`='1' and UserID<>?";
//    private final String INSERT_USER = "insert into user (UserName, `Password`) values (?,?)";
//    private final String INSERT_USER_ACCOUNT = "insert into user (UserID, UserName) values (?,?)";
//    private final String CHECK_USER = "select UserID from user where UserName =? limit 1";
//    //  Instance

    private final String LOGIN = "SELECT UserID, UserName, Password FROM user WHERE UserName = BINARY(?) AND Status = '1'";
    private final String SELECT_USER_ACCOUNT = "SELECT UserID, UserName FROM user WHERE Status = '1' AND UserID <> ?";
    private final String INSERT_USER = "INSERT INTO user (UserName, Password) VALUES (?, ?)";
//    private final String INSERT_USER_ACCOUNT = "INSERT INTO user_account (UserID, UserName) VALUES (?, ?)";
    private final String CHECK_USER = "SELECT UserID FROM user WHERE UserName = ? LIMIT 1";


    private final Connection con;
}

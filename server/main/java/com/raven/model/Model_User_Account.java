package com.raven.model;

//import com.raven.model.Model_Register;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class Model_User_Account {

    @Id
    @GeneratedValue
    @Column(name = "UserID")
    private int userID;

    @Column(name = "UserName")
    private String userName;


//    private String image;
    @Column(name = "Password")
    private String password;
    @Column(name = "Status")
    private boolean status;


    public Model_User_Account() {
    }

    public Model_User_Account(int userID, String userName, String password, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.status = status;
    }

    public Model_User_Account(int userID, String userName, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.status = status;
    }

    public Model_User_Account(String userName, boolean status) {
        this.userName = userName;
        this.status = status;
    }
//
    public Model_User_Account(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public Model_User_Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Model_User_Account{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
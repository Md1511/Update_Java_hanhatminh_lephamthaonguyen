///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.raven.model;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// *
// * @author Admin
// */
//public class Model_User_Group {
//       public Model_User_Group(int userID, String userName, boolean status) {
//        this.userID = userID;
//        this.userName = userName;
//        this.status = status;
//    }
//
//    public Model_User_Group(String userName, boolean status) {
//        this.userName = userName;
//        this.status = status;
//    }
//
//    public int getUserID() {
//        return userID;
//    }
//
//    public void setUserID(int userID) {
//        this.userID = userID;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public Model_User_Group(Object json) {
//        JSONObject obj = (JSONObject) json;
//        try {
//            userID = obj.getInt("userID");
//            userName = obj.getString("userName");
////            password = obj.getString("password");
////            image = obj.getString("image");
//            status = obj.getBoolean("status");
//        } catch (JSONException e) {
//            System.err.println(e);
//        }
//    }
//
//    private int userID;
//    private String userName;
////    private String password;
////    private String gender;
////    private String image;
//    private boolean status;
//}

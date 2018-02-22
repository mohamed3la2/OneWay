package com.example.eltimmy.oneway2;



public class UserInfo {
    public String Name ;
    public   String Phone_Number;
    public String Email;
    public String Uid ;
    public String downloadURL ;



    public UserInfo(String name, String phone, String email, String uid, String downloadUrl) {
        Name = name;
        this.Phone_Number = phone;
        this.Email = email;
        Uid = uid;
        this.downloadURL = downloadUrl;
    }
}

package com.example.a24hremergencyservices;

public class Cleaner {
    private String UserName;
    private String Tel;
    private String Email;
    private String Password;

    public Cleaner(String userName, String tel, String email, String password){
        UserName = userName;
        Tel = tel;
        Email = email;
        Password = password;
    }

    public Cleaner(){

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
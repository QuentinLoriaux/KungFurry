package com.ensta.myfilmlist.model;

public class Utilisateur {
    private long id;
    private String username;
    private String password;
    private long roleID;


    public Utilisateur() {
        this.id = 0;
        this.username = "";
        this.password = "";
        this.roleID = 0;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setRoleID(long id) {
        this.roleID = id;
    }

    public long getRoleID() {
        return roleID;
    }
}

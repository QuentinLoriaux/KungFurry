package com.ensta.myfilmlist.form;

import javax.validation.constraints.NotBlank;

public class UtilisateurForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private long role_id;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRoleId(long role_id) {
        this.role_id = role_id;
    }

    public long getRoleId() {
        return role_id;
    }


}

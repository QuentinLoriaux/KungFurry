package com.ensta.myfilmlist.model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    private String username;

    @Column
    private long role_id;

    @Column
    private String md5hex;

    public void setRoleId(long role_id) {
        this.role_id = role_id;
    }

    public long getRoleId() {
        return role_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            this.md5hex = DatatypeConverter
            .printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException ignored) {}
    }

    public void setMd5Hex(String md5hex) {
        this.md5hex = md5hex;
    }

    public String getMd5Hex(){
        return md5hex;
    }
}

package com.ensta.myfilmlist.model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.DatatypeConverter;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long role_id;
    private String username;
    private String md5hex;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

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
            String hash = DatatypeConverter
            .printHexBinary(digest).toLowerCase();
            this.md5hex = hash;
        } catch (NoSuchAlgorithmException e) {}
    }

    public void setMd5Hex(String md5hex) {
        this.md5hex = md5hex;
    }

    public String getMd5Hex(){
        return md5hex;
    }
}

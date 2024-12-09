package com.ensta.myfilmlist.dto;

/**
 * Contient les donnees d'un Film.
 */
public class UtilisateurDTO {

	private long id;
	private String username;
	private String md5hex;
	private long role_id;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getMd5Hex() {
		return md5hex;
	}
	
	public void setMd5Hex(String md5hex) {	
		this.md5hex = md5hex;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public long getRoleId() {
		return role_id;
	}
	public void setRoleId(long role_id) {
		this.role_id = role_id;
	}


	@Override
	public String toString() {
		return "FilmDTO [id=" + id + ", username=" + username + ", md5hex=" + md5hex + ", role_id=" + role_id + "]";
	}

}

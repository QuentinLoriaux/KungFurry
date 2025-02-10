package com.ensta.myfilmlist.dto;

/**
 * Contient les donnees d'un Film.
 */
public class FilmDTO {

	private long id;
	private String titre;
	private int duree;
	private String affiche;
	private double noteMoyenne;
	private int nbVues;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getAffiche() {
		return affiche;
	}
	public void setAffiche(String affiche) {
		this.affiche = affiche;
	}

	@Override
	public String toString() {
		return "FilmDTO [id=" + id + ", titre=" + titre + ", duree=" + duree + "]";
	}

	public double getNoteMoyenne() {
		return noteMoyenne;
	}

	public void setNoteMoyenne(double noteMoyenne) {
		this.noteMoyenne = noteMoyenne;
	}

	public int getNbVues() {
		return nbVues;
	}

	public void setNbVues(int nbVues) {
		this.nbVues = nbVues;
	}


}

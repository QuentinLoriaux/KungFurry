package com.ensta.myfilmlist.dto;

/**
 * Contient les donnees d'un Film.
 */
public class FilmDTO {

	private long id;
	private String titre;
	private int duree;
	private RealisateurDTO realisateur;
	private GenreDTO genre;
	private double noteMoyenne;

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

	public RealisateurDTO getRealisateur(){
		return realisateur;
	}
	public void setRealisateur(RealisateurDTO realisateur){
		this.realisateur = realisateur;
	}

	public GenreDTO getGenre(){
		return genre;
	}
	public void setGenre(GenreDTO genre){
		this.genre = genre;
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


}

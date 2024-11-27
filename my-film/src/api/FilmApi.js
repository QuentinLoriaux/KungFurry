import axios from 'axios';

const FILM_URI = 'http://localhost:8080/film';

/**
 * Fetch all films from the API.
 * @returns {Promise} Axios response promise.
 */
export function getAllFilms() {
    return axios.get(FILM_URI);
}

/**
 * Create a new film.
 * @param {Object} film - The film object to be created.
 * @returns {Promise} Axios response promise.
 */

// MUST FIX, NOT WORKING
export function postFilm(film) {
    const payload = {
        titre: film.title,              // Map title to titre
        duree: parseInt(film.duration),           // Map duration to duree
        realisateurId: film.realisateur // Map director to realisateur_id
    };
    console.log('payload:', payload);
    
    return axios.post(FILM_URI, payload);
}


/**
 * Update an existing film.
 * @param {number} id - The ID of the film to be updated.
 * @param {Object} film - The updated film object.
 * @returns {Promise} Axios response promise.
 */
export function putFilm(id, film) {
    return axios.put(`${FILM_URI}/${id}`, film);
}

/**
 * Delete a film by ID.
 * @param {number} id - The ID of the film to be deleted.
 * @returns {Promise} Axios response promise.
 */
export function deleteFilm(id) {
    return axios.delete(`${FILM_URI}/${id}`);
}

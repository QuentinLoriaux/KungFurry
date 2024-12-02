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

export function postFilm(film) {
    const payload = {
        titre: film.title,
        duree: parseInt(film.duration),
        realisateurId: film.realisateur
    };
    return axios.post(FILM_URI, payload);
}


/**
 * Update an existing film.
 * @param {number} filmId - The ID of the film to be updated.
 * @param {Object} film - The updated film object.
 * @returns {Promise} Axios response promise.
 */
export function putFilm(filmId, film) {
    const payload = {
        titre: film.title,
        duree: parseInt(film.duration),
        realisateurId: film.realisateur
    }
    console.log('payload:', payload);
    console.log('film:', film);
    console.log('film.id:', filmId);
    return axios.put(`${FILM_URI}/${filmId}`, payload);
}

/**
 * Delete a film by ID.
 * @param {number} id - The ID of the film to be deleted.
 * @returns {Promise} Axios response promise.
 */
export function deleteFilm(id) {
    return axios.delete(`${FILM_URI}/${id}`);
}

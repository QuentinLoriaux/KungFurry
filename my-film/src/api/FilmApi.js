import axios from 'axios';

const FILM_URI = 'http://localhost:8080/film';

/**
 * Fetch all films from the API.
 *
 * @param {number} page - The page number.
 * @param {number} size - The page size.
 * @returns {Promise} Axios response promise.
 */
export function getAllFilms( page, size) {
    return axios.get(FILM_URI, {
        params: { page: page, size: size },
    });
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
        realisateurId: film.realisateurId,
        genreId: film.genreId
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
        realisateurId: film.realisateurId,
        genreId: film.genreId
    }
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

/**
 * Get a film by ID.
 * @param {number} id - The ID of the film to be fetched.
 * @returns {Promise} Axios response promise.
 */
export function getFilmById(id) {
    return axios.get(`${FILM_URI}/${id}`);
}
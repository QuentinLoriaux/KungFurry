import axios from 'axios';

const FILM_URI = 'http://localhost:8080/film';

/**
 * Fetch all films from the API.
 *
 * @param {number} page - The page number.
 * @param {number} size - The page size.
 * @returns {Promise} Axios response promise.
 */
export function getAllFilms( page, size, query, sort, order) {
    return axios.get(FILM_URI, {
        params: { page: page, size: size, query: query, sort: sort, order: order },
    });
}

/**
 * Create a new film.
 * @param {Object} film - The film object to be created.
 * @param {string} token - The JWT token.
 * @returns {Promise} Axios response promise.
 */

export function postFilm(film, token) {
    const payload = {
        titre: film.title,
        duree: parseInt(film.duration),
        realisateurId: film.realisateurId,
        genreId: film.genreId
    };
    return axios.post(FILM_URI, payload, { headers: { Authorization: `Bearer ${token.token}` } })
        .catch(error => {
            console.error('Erreur lors de la création du film', error);
            throw error;
        });
}


/**
 * Update an existing film.
 * @param {number} filmId - The ID of the film to be updated.
 * @param {Object} film - The updated film object.
 * @param {string} token - The JWT token.
 * @returns {Promise} Axios response promise.
 */
export function putFilm(filmId, film, token) {
    const payload = {
        titre: film.title,
        duree: parseInt(film.duration),
        realisateurId: film.realisateurId,
        genreId: film.genreId
    }
    return axios.put(`${FILM_URI}/${filmId}`, payload, { headers: { Authorization: `Bearer ${token.token}` } })
        .catch(error => {
            console.error('Erreur lors de la mise à jour du film', error);
            throw error;
        });
}

/**
 * Delete a film by ID.
 * @param {number} id - The ID of the film to be deleted.
 * @param {string} token - The JWT token.
 * @returns {Promise} Axios response promise.
 */
export function deleteFilm(id, token) {
    return axios.delete(`${FILM_URI}/${id}`, { headers: { Authorization: `Bearer ${token.token}` } })
        .catch(error => {
            console.error('Erreur lors de la suppression du film', error);
            throw error;
        });
}

/**
 * Get a film by ID.
 * @param {number} id - The ID of the film to be fetched.
 * @returns {Promise} Axios response promise.
 */
export function getFilmById(id) {
    return axios.get(`${FILM_URI}/${id}`);
}
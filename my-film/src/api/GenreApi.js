import axios from 'axios';

const GENRE_URI = "http://localhost:8080/genres";

/**
 * Fetch all genres from the API.
 * @returns {Promise} Axios response promise.
 */
export function getAllGenres() {
    return axios.get(GENRE_URI);
}

/**
 * Find a genre by id.
 * @param {number} id - The ID of the genre to be found.
 * @returns {Promise} Axios response promise.
 */
export function getGenreById(id) {
    return axios.get(`${GENRE_URI}/${id}`);
}
import axios from 'axios';

const REALISATEUR_URI = 'http://localhost:8080/realisateur';

/**
 * Fetch all films from the API.
 * @returns {Promise} Axios response promise.
 */
export function getAllRealisateurs() {
    return axios.get(REALISATEUR_URI);
}


import axios from 'axios';

const REALISATEUR_URI = 'http://localhost:8080/realisateur';

/**
 * Fetch all films from the API.
 * @returns {Promise} Axios response promise.
 */
export const getAllRealisateurs = (page, size) => {
    return axios.get(REALISATEUR_URI, {
        params: { page: 1, size:50 },
    });
};

/**
 * Create a new realisateur.
 * @param {Object} realisateur - The realisateur object to be created.
 * @returns {Promise} Axios response promise.
 */
export function postRealisateur(realisateur) {
    const payload = {
        dateNaissance: realisateur.dateNaissance,
        nom: realisateur.nom,
        prenom: realisateur.prenom,
    };
    return axios.post(REALISATEUR_URI, payload);
}

/**
 * Find a realisateur by id.
 * @param {number} id - The ID of the realisateur to be found.
 * @returns {Promise} Axios response promise.
 */
export function getRealisateurById(id) {
    return axios.get(`${REALISATEUR_URI}/${id}`);
}

/**
 * Update an existing realisateur.
 * @param {number} realisateurId - The ID of the realisateur to be updated.
 * @param {Object} realisateur - The updated realisateur object.
 * @returns {Promise} Axios response promise.
 */
export function updateRealisateur(realisateurId, realisateur) {
    const payload = {
        dateNaissance: realisateur.dateNaissance,
        nom: realisateur.nom,
        prenom: realisateur.prenom,
    };
    return axios.put(`${REALISATEUR_URI}/${realisateurId}`, payload);
}

/**
 * Delete a realisateur by ID.
 * @param {number} id - The ID of the realisateur to be deleted.
 * @returns {Promise} Axios response promise.
 */
export function deleteRealisateur(id) {
    return axios.delete(`${REALISATEUR_URI}/${id}`);
}


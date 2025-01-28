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
 * @param {string} token - The JWT token.
 * @returns {Promise} Axios response promise.
 */
export function postRealisateur(realisateur, token) {
    const payload = {
        dateNaissance: realisateur.dateNaissance,
        nom: realisateur.nom,
        prenom: realisateur.prenom,
    };
    return axios.post(REALISATEUR_URI, payload, { params: {
            token: token === undefined ? '' : token.token,
        }, })
        .catch(error => {
            console.error('Erreur lors de la création du realisateur', error);
            throw error;
        });
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
 * @param {string} token - The JWT token.
 * @returns {Promise} Axios response promise.
 */
export function updateRealisateur(realisateurId, realisateur, token) {
    const payload = {
        dateNaissance: realisateur.dateNaissance,
        nom: realisateur.nom,
        prenom: realisateur.prenom,
    };
    return axios.put(`${REALISATEUR_URI}/${realisateurId}`, payload, { params: {
            token: token === undefined ? '' : token.token,
        }, })
        .catch(error => {
            console.error('Erreur lors de la mise à jour du realisateur', error);
            throw error;
        });
}

/**
 * Delete a realisateur by ID.
 * @param {number} id - The ID of the realisateur to be deleted.
 * @param {string} token - The JWT token.
 * @returns {Promise} Axios response promise.
 */
export function deleteRealisateur(id, token) {
    return axios.delete(`${REALISATEUR_URI}/${id}`, { params: {
            token: token === undefined ? '' : token.token,
        }, })
            .catch(error => {
            console.error('Erreur lors de la suppression du realisateur', error);
            throw error;
        });
}


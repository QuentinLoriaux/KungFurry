import axios from 'axios';

const COMMENTAIRE_URI = 'http://localhost:8080/commentaire';

/**
 * Add a new comment.
 * @param {string} comment - The comment object to be created.
 * @param {string} token - The JWT token.
 * @param {number} filmId - The ID of the film to be commented.
 * @returns {Promise} Axios response promise.
 */
export function postComment(comment, token, filmId) {
    const payload = {
        filmId: filmId,
        content : comment
    };
    return axios.post(COMMENTAIRE_URI, payload, { headers: { Authorization: `Bearer ${token.token}` }})
        .catch(error => {
            console.error('Erreur lors de la création du commentaire', error);
            throw error;
        });
}

/**
 * Delete an existing comment.
 * @param {number} commentId - The ID of the comment to be deleted.
 * @param {string} token - The JWT token.
 * @returns {Promise} Axios response promise.
 */
export function deleteCommentAPI(commentId, token) {
    return axios.delete(`${COMMENTAIRE_URI}/${commentId}`, { headers: { Authorization: `Bearer ${token.token}` } })
        .catch(error => {
            console.error('Erreur lors de la suppression du commentaire', error);
            throw error;
        });
}

/**
 * Update an existing comment.
 * @param {number} commentId - The ID of the comment to be updated.
 * @param {string} comment - The updated comment object.
 * @param {number} filmId - The ID of the film
 * @param {string} token - The JWT token.
 * @returns {Promise} Axios response promise.
 */
export function putComment(commentId, comment, filmId, token){
    const payload = {
        filmId: filmId,
        content : comment,
    };
    return axios.put(`${COMMENTAIRE_URI}/${commentId}`, payload, { headers: { Authorization: `Bearer ${token.token}` }})
}
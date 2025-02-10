import axios from 'axios';

const NOTE_URI = 'http://localhost:8080/notes';

/**
 * Add a new note.
 * @param {number} note - The value of the note.
 * @param {string} token - The JWT token.
 * @param {number} filmId - The ID of the film to be noted.
 *
 * @returns {Promise} Axios response promise.
 */
export function postNote(note, token, filmId) {
    const payload = {
        note: note,
        filmId: filmId
    };
    return axios.post(NOTE_URI, payload, { headers: { Authorization: `Bearer ${token.token}` }})
        .catch(error => {
            console.error('Erreur lors de la création de la note', error);
            throw error;
        });
}

/**
 * Delete an existing note.
 * @param {number} noteId - The ID of the note to be deleted.
 * @param {string} token - The JWT token.
 *
 * @returns {Promise} Axios response promise.
 */
export function deleteNote(noteId, token) {
    return axios.delete(`${NOTE_URI}/${noteId}`, { headers:
        { Authorization: `Bearer ${token.token}` } })
        .catch(error => {
            console.error('Erreur lors de la suppression de la note', error);
            throw error;
        });
}

/**
 * Update an existing note.
 * @param {number} noteId - The ID of the note to be updated.
 * @param {number} note - The updated note value.
 * @param {number} filmId - The ID of the film
 * @param {string} token - The JWT token.
 *
 * @returns {Promise} Axios response promise.
 */
export function putNote(noteId, note, filmId, token){
    const payload = {
        note: note,
        filmId: filmId
    };
    return axios.put(`${NOTE_URI}/${noteId}`, payload, { headers: { Authorization: `Bearer ${token.token}` } })
        .catch(error => {
            console.error('Erreur lors de la mise à jour de la note', error);
            throw error;
        });
}

/**
 * Get the note of a film by a user.
 * @param {number} filmId - The ID of the film.
 * @param {string} token - The JWT token.
 *
 * @returns {Promise} Axios response promise.
 */



import axios from "axios";

const USER_URI = "http://localhost:8080/user";

/**
 * Fetch all users from the API.
 * @param {string} token - The token of the user.
 * @returns {Promise} Axios response promise.
 */
export function getAllUsers(token) {
  return axios.get(USER_URI, {
    params: {
      token: token === undefined ? '' : token.token,
    },
  }).catch(error => {
    console.error('Erreur lors de la récupération des utilisateurs', error);
    throw error;
  });
}

/**
 * Create a new user.
 * @param {Object} user - The user object to be created.
 * @param {string} token - The token of the user.
 * @returns {Promise} Axios response promise.
 */
export function createUser(user, token) {
  const payload = {
    username: user.username,
    password: user.md5Hex,
    roleId : 0,
  };
  return axios.post(USER_URI, payload, { headers: { Authorization: `Bearer ${token}` } })
      .catch(error => {
      console.error('Erreur lors de la création de l\'utilisateur', error);
      throw error;
    });
}

/**
 * Find a user by id.
 * @param {number} id - The ID of the user to be found.
 * @param {string} token - The token of the user.
 * @returns {Promise} Axios response promise.
 */
export function getUserById(id, token) {
  return axios.get(`${USER_URI}/${id}`, {
    params: {
      token: token === undefined ? '' : token.token,
    },
  }).catch(error => {
    console.error('Erreur lors de la récupération de l\'utilisateur', error);
    throw error;
  });
}

/**
 * Update an existing user.
 * @param {number} userId - The ID of the user to be updated.
 * @param {Object} user - The updated user object.
 * @param {string} token - The token of the user.
 * @returns {Promise} Axios response promise.
 */
export function updateUser(userId, user, token) {
  const payload = {
    username: user.username,
    password: user.md5Hex,
    roleId : user.roleId,
  };
  return axios.put(`${USER_URI}/${userId}`, payload, { params: {
      token: token === undefined ? '' : token.token,
    }, })
      .catch(error => {
      console.error('Erreur lors de la mise à jour de l\'utilisateur', error);
      throw error;
    });
}

/**
 * Delete a user by ID.
 * @param {number} id - The ID of the user to be deleted.
 * @param {string} token - The token of the user.
 * @returns {Promise} Axios response promise.
 */
export function deleteUser(id, token) {
    return axios.delete(`${USER_URI}/${id}`, { params: {
        token: token === undefined ? '' : token.token,
      }, }).catch(error => {
    console.error('Erreur lors de la suppression de l\'utilisateur', error);
    });
}
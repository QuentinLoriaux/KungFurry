import axios from "axios";

const USER_URI = "http://localhost:8080/user";

/**
 * Fetch all users from the API.
 * @returns {Promise} Axios response promise.
 */
export function getAllUsers() {
  return axios.get(USER_URI);
}

/**
 * Create a new user.
 * @param {Object} user - The user object to be created.
 * @returns {Promise} Axios response promise.
 */
export function createUser(user) {
  const payload = {
    username: user.username,
    password: user.md5Hex,
    roleId : 0,
  };
  return axios.post(USER_URI, payload);
}

/**
 * Find a user by id.
 * @param {number} id - The ID of the user to be found.
 * @returns {Promise} Axios response promise.
 */
export function getUserById(id) {
  return axios.get(`${USER_URI}/${id}`);
}

/**
 * Update an existing user.
 * @param {number} userId - The ID of the user to be updated.
 * @param {Object} user - The updated user object.
 * @returns {Promise} Axios response promise.
 */
export function updateUser(userId, user) {
  const payload = {
    username: user.username,
    password: user.md5Hex,
    roleId : user.roleId,
  };
  return axios.put(`${USER_URI}/${userId}`, payload);
}

/**
 * Delete a user by ID.
 * @param {number} id - The ID of the user to be deleted.
 * @returns {Promise} Axios response promise.
 */
export function deleteUser(id) {
  return axios.delete(`${USER_URI}/${id}`);
}
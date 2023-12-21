const UserModel = require("../models/userModel");

const registerUser = async (userId, userData) => {
  try {
    const response = await UserModel.addUser(userId, userData);
    return response;
  } catch (error) {
    throw error;
  }
};

const getUserDetails = async (userId) => {
  try {
    const userDetails = await UserModel.getUserById(userId);
    return userDetails;
  } catch (error) {
    throw error;
  }
};

const getUserDetailsById = async (userId) => {
  try {
    const userDetails = await UserModel.getUserById(userId);
    return userDetails;
  } catch (error) {
    throw error;
  }
};

module.exports = {
  registerUser,
  getUserDetails,
  getUserDetailsById,
};

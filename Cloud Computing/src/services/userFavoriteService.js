const UserFavoritesModel = require("../models/userFavoriteModel");

// Mengambil daftar resep yang disukai oleh pengguna tertentu
const getFavoriteRecipes = async (userId) => {
  try {
    const favoriteRecipes = await UserFavoritesModel.getFavoriteRecipes(userId);
    return favoriteRecipes;
  } catch (error) {
    throw error;
  }
};

// Menambahkan resep ke dalam daftar favorit pengguna
const addFavoriteRecipe = async (userId, recipeId) => {
  try {
    const response = await UserFavoritesModel.addFavoriteRecipe(userId, recipeId);
    return response;
  } catch (error) {
    throw error;
  }
};

module.exports = {
  addFavoriteRecipe,
  getFavoriteRecipes,
};

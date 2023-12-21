const RecipeModel = require("../models/recipeModel");

const getAllRecipes = async () => {
  try {
    const recipes = await RecipeModel.getAllRecipes();
    return recipes;
  } catch (error) {
    throw error;
  }
};

const getRecipeById = async (recipeId) => {
  try {
    const recipe = await RecipeModel.getRecipeById(recipeId);
    return recipe;
  } catch (error) {
    throw error;
  }
};

module.exports = {
  getAllRecipes,
  getRecipeById,
};

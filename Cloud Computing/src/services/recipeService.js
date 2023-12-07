const RecipeModel = require("../models/recipeModel");

const getAllRecipes = async () => {
  try {
    const recipes = await RecipeModel.getAllRecipes();
    return recipes;
  } catch (error) {
    throw error;
  }
};

module.exports = {
  getAllRecipes,
};

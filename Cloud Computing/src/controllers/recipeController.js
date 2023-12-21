const { getAllRecipes, getRecipeById } = require("../services/recipeService");

const getRecipes = async (req, res) => {
  try {
    const recipes = await getAllRecipes();
    res.status(200).json(recipes);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

const getRecipeByIdHandler = async (req, res) => {
  const { id } = req.params;

  try {
    const recipe = await getRecipeById(id);
    res.status(200).json(recipe);
  } catch (error) {
    res.status(404).json({ error: error.message });
  }
};

module.exports = {
  getRecipes,
  getRecipeByIdHandler,
};

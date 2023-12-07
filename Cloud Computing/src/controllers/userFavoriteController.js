const UserFavoritesService = require("../services/userFavoriteService");

// Mengembalikan daftar resep yang disukai oleh pengguna tertentu
exports.getFavoriteRecipes = async (req, res) => {
  try {
    const userId = req.userId; 
    const favoriteRecipes = await UserFavoritesService.getFavoriteRecipes(userId);

    res.status(200).json({ favoriteRecipes });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Menambahkan resep ke dalam daftar favorit pengguna
exports.addFavoriteRecipe = async (req, res) => {
  try {
    const userId = req.userId; 
    const { recipeId } = req.body;

    if (!recipeId) {
      return res.status(400).json({ error: 'Recipe ID is required' });
    }

    const response = await UserFavoritesService.addFavoriteRecipe(userId, recipeId);
    res.status(201).json(response);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

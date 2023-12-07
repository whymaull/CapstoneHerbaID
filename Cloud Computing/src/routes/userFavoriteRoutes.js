const express = require("express");
const { authMiddleware } = require("../middlewares/authMiddleware");
const UserFavoriteController = require("../controllers/userFavoriteController");

const router = express.Router();

// get resep favorit pengguna
router.get("/", authMiddleware, UserFavoriteController.getFavoriteRecipes);

// menambahkan resep ke favorit pengguna
router.post("/add", authMiddleware, UserFavoriteController.addFavoriteRecipe);

module.exports = router;

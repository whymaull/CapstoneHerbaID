const express = require("express");
const { getRecipes, getRecipeByIdHandler } = require("../controllers/recipeController");
const { authMiddleware } = require("../middlewares/authMiddleware");

const router = express.Router();

router.get("/", authMiddleware, getRecipes);
router.get("/:id", authMiddleware, getRecipeByIdHandler);

module.exports = router;

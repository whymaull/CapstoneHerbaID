const express = require("express");
const { getRecipes } = require("../controllers/recipeController");
const { authMiddleware } = require("../middlewares/authMiddleware");

const router = express.Router();

router.get("/", authMiddleware, getRecipes);

module.exports = router;

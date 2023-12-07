const express = require("express");
const { addComplaint, getRecommendedRecipes } = require("../controllers/complaintController");
const { authMiddleware } = require("../middlewares/authMiddleware");

const router = express.Router();

router.post("/", authMiddleware, addComplaint);
router.post("/recommended", authMiddleware, getRecommendedRecipes);

module.exports = router;

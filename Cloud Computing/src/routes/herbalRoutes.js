const express = require("express");
const multer = require("multer");

const HerbalController = require("../controllers/herbalController");
const { authMiddleware } = require("../middlewares/authMiddleware");

const router = express.Router();
const upload = multer();

// Endpoint untuk identifikasi herbal
router.post("/identify", authMiddleware, upload.single("herbalImage"), HerbalController.identifyHerbal);

module.exports = router;

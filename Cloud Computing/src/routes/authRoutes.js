const express = require("express");
const { signup, signin, logout, getUserDetailsById } = require("../controllers/auth");
const { authMiddleware } = require("../middlewares/authMiddleware");

const router = express.Router();

// Daftar pengguna baru
router.post("/signup", signup);

// Masuk dengan email dan password
router.post("/signin", signin);

// Keluar dari sesi pengguna
router.post("/logout", authMiddleware, logout); 

// Mendapatkan detail pengguna berdasarkan ID
router.get("/user/:id", authMiddleware, getUserDetailsById);

module.exports = router;

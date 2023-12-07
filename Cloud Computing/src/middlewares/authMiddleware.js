require("dotenv").config();
const jwt = require("jsonwebtoken");
const { getUserById } = require("../models/userModel");

async function authMiddleware(req, res, next) {
  let token;
  if (req.headers.authorization && req.headers.authorization.startsWith("Bearer")) {
    try {
      token = req.headers.authorization.split(" ")[1];
      if (!token) {
        return res.status(401).json({ error: true, message: "Unauthorized, no token" });
      }
      const decoded = jwt.verify(token, process.env.JWT_SECRET);
      if (!decoded) {
        return res.status(401).json({ error: true, message: "Unauthorized, invalid token" });
      }
      const user = await getUserById(decoded.userId);
      if (!user) {
        return res.status(401).json({ error: true, message: "Unauthorized, no user" });
      }
      req.userId = decoded.userId;
      next();
    } catch (error) {
      return res.status(401).json({ error: true, message: "Unauthorized, token failed" });
    }
  } else {
    return res.status(401).json({ error: true, message: "Unauthorized, token not found" });
  }
}

module.exports = {
  authMiddleware,
};

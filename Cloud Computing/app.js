require("dotenv").config();
const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const authRoutes = require("./src/routes/authRoutes");
const herbalRoutes = require("./src/routes/herbalRoutes");
const recipeRoutes = require("./src/routes/recipeRoutes");
const complaintRoutes = require("./src/routes/complaintRoutes");
const userFavoriteRoutes = require('./src/routes/userFavoriteRoutes');

const app = express();
const port = process.env.PORT || 5000;

const corsOptions = {
  origin: "*",
  credentials: true,
  optionSuccessStatus: 200,
};

app.use(express.json());
app.use(cors(corsOptions));
app.use(bodyParser.urlencoded({ extended: true }));

// Routes
app.use("/api/auth", authRoutes);
app.use("/api/herbal", herbalRoutes);
app.use("/api/recipe", recipeRoutes);
app.use("/api/complaint", complaintRoutes);
app.use("/api/user/favorites", userFavoriteRoutes);

app.get("/", (req, res) => {
  res.send("Welcome to herbalid backend!");
});

app.listen(port, () => {
  console.log(`HerbalID app listening on port ${port}`);
});

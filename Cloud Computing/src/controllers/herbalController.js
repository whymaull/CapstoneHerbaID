const { storage, uploadBytes, getDownloadURL, ref } = require("../config/firebase");
const HerbalModel = require("../models/herbalModel");
const HerbalService = require("../services/herbalService");

// Fungsi untuk mengunggah gambar ke Firebase Storage
const uploadImageToStorage = async (file) => {
  try {
    const storageRef = ref(storage, "image-identify");
    const fileName = file.name;
    const imageRef = ref(storageRef, fileName);

    await uploadBytes(imageRef, file);

    const imageUrl = await getDownloadURL(imageRef);
    console.log("Image uploaded. URL:", imageUrl);
    return imageUrl;
  } catch (error) {
    console.error("Error uploading image:", error);
    throw error;
  }
};

// Fungsi untuk identifikasi herbal dari gambar yang diunggah
exports.identifyHerbal = async (req, res) => {
  try {
    if (!req.file) {
      return res.status(400).json({ error: "No image provided" });
    }

    const imageUrl = await uploadImageToStorage(req.file);

    const identifiedHerbal = "Nama Herbal yang Teridentifikasi";

    if (identifiedHerbal && typeof identifiedHerbal === "string") {
      const herbalData = await HerbalModel.getHerbalByName(identifiedHerbal);

      if (herbalData) {
        const recipes = await HerbalService.getRecipesByHerbalId(herbalData.recipeIds);

        res.status(200).json({
          herbalData: {
            herbalId: herbalData.herbalId,
            name: herbalData.name,
            imageURL: imageUrl,
            about: herbalData.about,
            benefits: herbalData.benefits,
            recipeIds: herbalData.recipeIds,
          },
          recipes: recipes,
        });
      } else {
        res.status(404).json({ message: "Herbal not found" });
      }
    } else {
      res.status(400).json({ error: "Invalid identified herbal name" });
    }
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

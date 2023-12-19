const { addComplaintToFirestore, getRecommendedRecipesFromDatabase } = require("../models/complaintModel");

exports.addComplaint = async (complaintType, userId) => {
  try {
    await addComplaintToFirestore(complaintType, userId);
    const recommendedRecipes = await getRecommendedRecipesFromDatabase(complaintType);
    return { message: "Keluhan berhasil ditambahkan", recommendedRecipes };
  } catch (error) {
    throw error;
  }
};

exports.getRecommendedRecipes = async (complaintType) => {
  try {
    const recommendedRecipes = await getRecommendedRecipesFromDatabase(complaintType);
    return recommendedRecipes;
  } catch (error) {
    throw error;
  }
};

const { db, collection, getDocs } = require("../config/firebase");

const getAllRecipes = async () => {
  try {
    const recipesCollection = collection(db, "Recipes");
    const recipesSnapshot = await getDocs(recipesCollection);

    const recipes = [];
    recipesSnapshot.forEach((doc) => {
      recipes.push({ recipeId: doc.id, ...doc.data() });
    });

    return { recipes };
  } catch (error) {
    throw error;
  }
};

module.exports = {
  getAllRecipes,
};

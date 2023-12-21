const { db, collection, getDocs, getDoc, doc } = require("../config/firebase");

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

const getRecipeById = async (recipeId) => {
  try {
    const recipeRef = doc(db, "Recipes", recipeId); 
    const recipeDoc = await getDoc(recipeRef);

    if (!recipeDoc.exists()) {
      throw new Error("Recipe not found");
    }

    return { recipeId: recipeDoc.id, ...recipeDoc.data() };
  } catch (error) {
    throw error;
  }
};

module.exports = {
  getAllRecipes,
  getRecipeById,
};

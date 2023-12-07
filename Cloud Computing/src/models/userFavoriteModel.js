const { db, collection, query, where, getDocs, setDoc, doc } = require("../config/firebase");

// Mendapatkan daftar resep yang disimpan sebagai favorit oleh pengguna tertentu
const getFavoriteRecipes = async (userId) => {
  try {
    const favoritesCollection = collection(db, "userFavorites");
    const q = query(favoritesCollection, where("userId", "==", userId));
    const favoritesSnapshot = await getDocs(q);

    const favoriteRecipes = [];

    for (const docRef of favoritesSnapshot.docs) {
      favoriteRecipes.push(docRef.data());
    }

    return favoriteRecipes;
  } catch (error) {
    throw error;
  }
};

// Menambahkan resep ke dalam daftar favorit pengguna
const addFavoriteRecipe = async (userId, recipeId) => {
  try {
    const userFavoritesRef = collection(db, "userFavorites");
    const newFavorite = { userId, recipeId };
    await setDoc(doc(userFavoritesRef), newFavorite);

    return { message: "Recipe added to favorites successfully" };
  } catch (error) {
    throw error;
  }
};

module.exports = {
  addFavoriteRecipe,
  getFavoriteRecipes,
};

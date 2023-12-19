const { db, collection, doc, getDoc } = require("../config/firebase");

// Dapatkan data herbal berdasarkan ID
const getHerbalById = async (herbalId) => {
  try {
    if (!herbalId) {
      throw new Error("Invalid herbal ID provided");
    }

    const herbalRef = doc(db, "herbals", herbalId);
    const herbalSnapshot = await getDoc(herbalRef);

    if (herbalSnapshot.exists()) {
      return herbalSnapshot.data();
    } else {
      throw new Error("Herbal not found");
    }
  } catch (error) {
    throw error;
  }
};

// Dapatkan data herbal berdasarkan nama herbal
const getHerbalByName = async (herbalName) => {
  try {
    if (!herbalName) {
      throw new Error("Invalid herbal name provided");
    }

    const herbalsRef = collection(db, "herbals");
    let q;

    if (herbalName) {
      q = query(herbalsRef, where("name", "==", herbalName));
    } else {
      throw new Error("Invalid herbal name provided");
    }

    const querySnapshot = await getDocs(q);

    if (!querySnapshot.empty) {
      const herbalData = querySnapshot.docs[0].data();
      return herbalData;
    } else {
      return null;
    }
  } catch (error) {
    throw error;
  }
};

module.exports = {
  getHerbalById,
  getHerbalByName,
};

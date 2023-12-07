const { identifyHerbalML, saveImageToStorage } = require("../utils/imageProcessing");
const { db, collection, query, where, getDocs } = require("../config/firebase");

// Mengunggah gambar herbal ke storage
exports.uploadImage = async (imageFile) => {
  try {
    const imageUrl = await saveImageToStorage(imageFile);
    return imageUrl;
  } catch (error) {
    throw error;
  }
};

// Mengidentifikasi herbal berdasarkan gambar menggunakan machine learning 
exports.identifyHerbal = async (imageUrl) => {
  try {
    const identifiedHerbal = await identifyHerbalML(imageUrl); // Fungsi identifikasi menggunakan ML 

    const herbalsRef = collection(db, "herbals");
    const q = query(herbalsRef, where("name", "==", identifiedHerbal));
    const querySnapshot = await getDocs(q);

    if (querySnapshot.empty) {
      throw new Error("Herbal not found");
    }

    // Mengambil data herbal dari Firestore
    const herbalData = querySnapshot.docs[0].data();
    return herbalData;
  } catch (error) {
    throw error;
  }
};

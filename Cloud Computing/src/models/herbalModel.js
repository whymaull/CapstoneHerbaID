const { db, doc, getDoc } = require('../config/firebase');

// Mengambil data herbal dari database berdasarkan ID herbal
exports.getHerbalData = async (herbalId) => {
  try {
    const herbalRef = doc(db, 'herbals', herbalId);
    const herbalSnapshot = await getDoc(herbalRef);

    if (herbalSnapshot.exists()) {
      const herbalData = herbalSnapshot.data();
      const processedData = processHerbalData(herbalData);
      return processedData;
    } else {
      throw new Error('Herbal not found');
    }
  } catch (error) {
    throw error;
  }
};

// Fungsi untuk memproses data herbal jika diperlukan
const processHerbalData = (herbalData) => {
  const processedData = {
    herbalId: herbalData.herbalId,
    name: herbalData.name,
    imageURL: herbalData.imageURL,
    about: herbalData.about,
    benefits: herbalData.benefits,
  };
  return processedData;
};

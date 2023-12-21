const { db, doc, setDoc, getDoc, getFirestore, collection, query, where, getDocs } = require("../config/firebase");

const addUser = async (userId, userData) => {
  try {
    const userRef = doc(db, "users", userId);
    await setDoc(userRef, userData);
    return { message: "User added successfully" };
  } catch (error) {
    throw error;
  }
};

const getUserById = async (userId) => {
  try {
    const userRef = doc(db, "users", userId);
    const userSnapshot = await getDoc(userRef);
    if (userSnapshot.exists()) {
      return { userId: userSnapshot.id, ...userSnapshot.data() };
    } else {
      return null;
    }
  } catch (error) {
    throw error;
  }
};

const getUserByEmail = async (email) => {
  try {
    const db = getFirestore(); // Inisialisasi koneksi ke Firestore
    const usersCollection = collection(db, "users");
    const q = query(usersCollection, where("email", "==", email));
    const userSnapshot = await getDocs(q);

    if (userSnapshot.empty) {
      return null;
    }

    return { userId: userSnapshot.docs[0].id, ...userSnapshot.docs[0].data() };
  } catch (error) {
    throw error;
  }
};

module.exports = {
  addUser,
  getUserById,
  getUserByEmail,
};

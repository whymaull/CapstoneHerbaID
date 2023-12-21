const { auth, createUserWithEmailAndPassword, signInWithEmailAndPassword, signOut, getDocs, db, collection, setDoc, doc } = require("../config/firebase");
const jwt = require("jsonwebtoken");
const { getUserByEmail, getUserById } = require("../models/userModel");

// Daftar pengguna baru
exports.signup = async (req, res) => {
  const { email, password, username } = req.body;

  try {
    const userCredential = await createUserWithEmailAndPassword(auth, email, password);

    await addUserDataToFirestore(userCredential.user.uid, email, username);

    res.status(201).json(userCredential);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Fungsi untuk menambahkan data pengguna ke Firestore
const addUserDataToFirestore = async (userId, email, username) => {
  try {
    const userRef = doc(db, "users", userId);
    await setDoc(userRef, { email, username });
  } catch (error) {
    throw error;
  }
};

// Masuk dengan email dan password
exports.signin = async (req, res) => {
  const { email, password } = req.body;

  try {
    const userCredential = await signInWithEmailAndPassword(auth, email, password);
    const user = await getUserByEmail(email);

    if (user) {
      const token = jwt.sign({ userId: user.userId }, process.env.JWT_SECRET, { expiresIn: "1h" });
      res.status(200).json({ token });
    } else {
      return res.status(401).json({ error: "User not found" });
    }
  } catch (error) {
    if (error.code === "auth/wrong-password") {
      return exports.handleLoginError("Incorrect password", res);
    } else if (error.code === "auth/invalid-email") {
      return handleLoginError("Invalid email address", res);
    } else if (error.code === "auth/user-not-found") {
      return exports.handleLoginError("User not found", res);
    }
    res.status(500).json({ error: error.message });
  }
};

// Keluar dari sesi pengguna
exports.logout = async (req, res) => {
  try {
    await signOut(auth);
    res.status(200).json({ message: "Logout successful" });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Mendapatkan detail pengguna berdasarkan ID
exports.getUserDetailsById = async (req, res) => {
  const userId = req.params.id;

  try {
    const user = await getUserById(userId);

    if (!user) {
      return res.status(404).json({ error: "User not found" });
    }

    res.status(200).json({ user });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

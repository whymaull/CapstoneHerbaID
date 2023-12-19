const { addComplaint, getRecommendedRecipes } = require("../services/complaintService");

exports.addComplaint = async (req, res) => {
  const { complaintType } = req.body;
  const userId = req.userId; 

  try {
    const response = await addComplaint(complaintType, userId);
    res.status(201).json(response);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.getRecommendedRecipes = async (req, res) => {
  const { complaintType } = req.body;

  try {
    const recommendedRecipes = await getRecommendedRecipes(complaintType);
    res.status(200).json({ recommendedRecipes });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

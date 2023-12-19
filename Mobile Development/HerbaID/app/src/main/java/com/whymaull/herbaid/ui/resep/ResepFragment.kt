package com.whymaull.herbaid.ui.resep

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.whymaull.herbaid.R
import com.whymaull.herbaid.data.adapter.RecipesAdapter
import com.whymaull.herbaid.data.database.resep.Recipes
import com.whymaull.herbaid.databinding.FragmentResepBinding

@Suppress("UNCHECKED_CAST")
class ResepFragment : Fragment() {

    private var _binding: FragmentResepBinding? = null
    private val binding get() = _binding!!
    private val db = FirebaseFirestore.getInstance()
    private val recipes = mutableListOf<Recipes>()
    private lateinit var adapter: RecipesAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResepBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView : RecyclerView = view.findViewById(R.id.rv_resep)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = RecipesAdapter(recipes)
        recyclerView.adapter = adapter

        fetchDataFromFirestore()
        return view
    }

    private fun fetchDataFromFirestore() {
        db.collection("Recipes")
            .get()
            .addOnSuccessListener { result ->
                processRecipes(result)
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "Error getting documents: ", exception)
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun processRecipes(result: QuerySnapshot) {
        for (document in result) {
            val recipe = Recipes (
                complaintType = document.get("complaintType") as? List<String>?,
                image = document.getString("image"),
                ingredients = document.get("ingredients") as? List<String>?,
                instructions = document.get("instructions") as? List<String>?,
                name = document.getString("name"),
                preparationTime = document.getString("preparationTime"),
                recipeId = document.getString("recipeId")
            )
            recipes.add(recipe)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
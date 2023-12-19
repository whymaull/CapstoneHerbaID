package com.whymaull.herbaid.data.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.whymaull.herbaid.R
import com.whymaull.herbaid.data.database.resep.Recipes
import com.whymaull.herbaid.ui.detail.DetailFragment

// RecipeAdapter.kt
class RecipesAdapter(private val recipes: List<Recipes>) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.imgResep)
        val recipeName: TextView = itemView.findViewById(R.id.tvNamaResep)
        val recipePreparationTime: TextView = itemView.findViewById(R.id.tvKomposisiResep)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_list_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]

        // Setel data ke dalam CardView
        Glide.with(holder.itemView.context).load(recipe.image).into(holder.recipeImage)
        holder.recipeName.text = recipe.name
        holder.recipePreparationTime.text = recipe.preparationTime

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("recipe", recipe)

            val detailResepFragment = DetailFragment()
            detailResepFragment.arguments = bundle

            val transaction = (holder.itemView.context as AppCompatActivity).supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, detailResepFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}

package com.whymaull.herbaid.data.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.whymaull.herbaid.R
import com.whymaull.herbaid.data.database.resep.Recipes
import com.whymaull.herbaid.data.response.RecipesItem
import com.whymaull.herbaid.databinding.CardListRecipeBinding
import com.whymaull.herbaid.ui.detail.DetailFragment


class RecipesAdapter() :ListAdapter<RecipesItem, RecipesAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CardListRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resep = getItem(position)
        holder.bind(resep)
    }

    class ViewHolder(
        private val binding: CardListRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(resep: RecipesItem) {
            binding.apply {

                tvNamaResep.text = resep.name
                tvKomposisiResep.text = resep.ingredients?.size.toString()+" Bahan"
                Glide.with(imgResep.context)
                    .load(resep.image)
                    .into(imgResep)

                btnToDetail.setOnClickListener{
                    val mBundle = Bundle()
                    mBundle.putString(DetailFragment.EXTRA_NAME, resep.recipeId)
                    val mDetailFragment = DetailFragment()
                    mDetailFragment.arguments = mBundle
                    val mFragmentManager = (itemView.context as AppCompatActivity).supportFragmentManager
                    mFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_container, mDetailFragment, DetailFragment::class.java.simpleName)
                        addToBackStack(null)
                        commit()
                    }
                }
            }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RecipesItem>() {
            override fun areItemsTheSame(oldItem: RecipesItem, newItem: RecipesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RecipesItem,
                newItem: RecipesItem,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

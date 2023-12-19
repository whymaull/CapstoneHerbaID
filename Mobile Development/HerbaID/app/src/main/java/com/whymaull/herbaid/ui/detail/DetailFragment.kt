package com.whymaull.herbaid.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.whymaull.herbaid.R
import com.whymaull.herbaid.data.database.resep.Recipes
import com.whymaull.herbaid.databinding.FragmentDetailBinding
import com.whymaull.herbaid.databinding.FragmentResepBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val recipe = arguments?.getSerializable("recipe") as Recipes?
        if (recipe != null) {
            val imgGambarResep : ImageView = binding.imgResepDetail
            Glide.with(this)
                .load(recipe.image)
                .into(imgGambarResep)
            val tvNamaResep : TextView = binding.tvNamaResepDetail
            tvNamaResep.text = recipe.name
            val tvPreparationTime : TextView = binding.tvPrepTime
            tvPreparationTime.text = recipe.preparationTime
            val tvKomposisiResep : TextView = binding.tvBahanBahan
            tvKomposisiResep.text = recipe.ingredients.toString()
            val tvAllKomposisi : TextView = binding.tvAllBahan
            tvAllKomposisi.text = recipe.ingredients.toString()
            val tvCaraResep : TextView = binding.tvCaraResep
            tvCaraResep.text = recipe.instructions.toString()
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
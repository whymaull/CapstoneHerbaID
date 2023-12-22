package com.whymaull.herbaid.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.whymaull.herbaid.data.response.DetailRecipeResponse
import com.whymaull.herbaid.databinding.FragmentDetailBinding
import com.whymaull.herbaid.ui.ViewModelFactory

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString(EXTRA_NAME)
        if (id != null) {
            viewModel.getSession().observe(viewLifecycleOwner) { setting ->
                if (setting.token.isNotEmpty()) {
                    viewModel.getDetailRecipe(setting.token, id)
                }
            }
        }

        viewModel.getDetailRecipe.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                setDetailResep(result)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDetailResep(result: DetailRecipeResponse) {
        binding.apply {
            tvNamaResepDetail.text = result.name
            tvPrepTime.text = result.preparationTime
            tvBahanBahan.text = result.ingredients?.size.toString()+" Bahan"
            val ingredientsList = result.ingredients
            if (!ingredientsList.isNullOrEmpty()) {
                val ingredientsStringBuilder = StringBuilder()
                for (ingredient in ingredientsList) {
                    ingredientsStringBuilder.append("- $ingredient\n")
                }
                tvAllBahan.text =  ingredientsStringBuilder
            }
            val instructionsList = result.instructions
            if (!instructionsList.isNullOrEmpty()) {
                val instructionsStringBuilder = StringBuilder()
                for ((index, instruction) in instructionsList.withIndex()) {
                    instructionsStringBuilder.append("- $instruction\n")
                }
                tvCaraBuat.text = instructionsStringBuilder
            }
            Glide.with(this@DetailFragment)
                .load(result.image)
                .into(imgResepDetail)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

}
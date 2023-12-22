package com.whymaull.herbaid.ui.resep

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.whymaull.herbaid.R
import com.whymaull.herbaid.data.adapter.RecipesAdapter
import com.whymaull.herbaid.data.database.resep.Recipes
import com.whymaull.herbaid.databinding.FragmentResepBinding
import com.whymaull.herbaid.ui.ViewModelFactory


class ResepFragment : Fragment() {

    private var _binding: FragmentResepBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ResepViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResepBinding.inflate(inflater, container, false)
        val view = binding.root
        val recyclerView : RecyclerView = view.findViewById(R.id.rv_resep)
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecipesAdapter()
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = view.findViewById(R.id.rv_resep)
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = RecipesAdapter()
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        viewModel.getSession().observe(viewLifecycleOwner) { setting ->
            if (setting.token.isNotEmpty()) {
                viewModel.getResep(setting.token)
            }
        }

        viewModel.listResep.observe(viewLifecycleOwner) { result ->
            Log.i("DapetGa", "onViewCreated: $result")
            adapter.submitList(result)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.whymaull.herbaid.ui.profile

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.whymaull.herbaid.databinding.FragmentProfileBinding
import com.whymaull.herbaid.ui.ViewModelFactory
import com.whymaull.herbaid.ui.login.LoginActivity

class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogout = binding.btnLogout

        val buttonText = btnLogout.text.toString()
        val spannableString = SpannableString(buttonText)
        spannableString.setSpan(UnderlineSpan(), 0, spannableString.length, 0)
        btnLogout.text = spannableString

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            Intent(requireContext(), LoginActivity::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
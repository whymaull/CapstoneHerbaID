package com.whymaull.herbaid.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.whymaull.herbaid.R
import com.whymaull.herbaid.databinding.ActivityRegisterBinding
import com.whymaull.herbaid.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        viewModel.isLoading.observe(this) {
            load(it)
        }

        initAction()
    }

    private fun initAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.namaEditText.text.toString().trim()
            val email = binding.emailEditText.text.toString().trim()
            val pass = binding.passwordEditText.text.toString().trim()

            when {
                name.isBlank() -> {
                    binding.namaEditText.requestFocus()
                    binding.namaEditText.error = getString(R.string.error_empty_name)
                }

                email.isBlank() -> {
                    binding.emailEditText.requestFocus()
                    binding.emailEditText.error = getString(R.string.error_empty_email)
                }

                !email.isEmailValid() -> {
                    binding.emailEditText.requestFocus()
                    binding.emailEditText.error = getString(R.string.error_invalid_email)
                }

                pass.isBlank() -> {
                    binding.passwordEditText.requestFocus()
                    binding.passwordEditText.error = getString(R.string.error_empty_password)
                }

                pass.length < 8 -> {
                    binding.passwordEditText.requestFocus()
                    binding.passwordEditText.error = getString(R.string.error_short_password)
                }

                else -> {
                    viewModel.registerUser(name, email, pass)
                    viewModel.isMessage.observe(this) { isSuccess ->
                        Log.i("kalosukses", "$isSuccess ")
                        if (isSuccess == null) {
                            messageToast(getString(R.string.message_register_success))
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()

                        } else if (isSuccess == getString(R.string.email_is_already_taken)) {
                            messageToast(getString(R.string.email_sudah_ada))
                        }
                    }
                }
            }
        }
        binding.tvToLogin.setOnClickListener {
            LoginActivity.start(this)
        }
    }

    private fun messageToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    private fun load(result: Boolean) {
        if (result) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }
}
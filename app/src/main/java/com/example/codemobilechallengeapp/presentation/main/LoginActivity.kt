package com.example.codemobilechallengeapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.codemobilechallengeapp.R
import com.example.codemobilechallengeapp.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var viewBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_login, null, false)
        viewBinding.lifecycleOwner = this
        setContentView(viewBinding.root)

        viewBinding.viewModel = viewModel
        viewModel.context.value = this@LoginActivity

        start()
        observeData()
    }

    private fun start() {
        viewBinding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.email.value = s.toString()
                viewModel.isReadyToLogin()
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
//
        viewBinding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                viewModel.password.value = s.toString()
                viewModel.isReadyToLogin()
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun observeData() {
        // Set up loading.
        viewModel.dataLoading.observe(
            this
        ) { loading -> viewBinding.loading.root.isVisible = loading }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT ).show()
        }

        viewModel.readyToLogin.observe(this) { isEnable ->
            viewBinding.btnLogin.isEnabled = isEnable
        }

        viewModel.navigateToMain.observe(this) { isNavigate ->
            if (isNavigate) {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
        }
    }
}
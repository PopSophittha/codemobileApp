package com.example.codemobilechallengeapp.presentation.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.codemobilechallengeapp.R
import com.example.codemobilechallengeapp.databinding.ActivityDetailBinding
import com.example.codemobilechallengeapp.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var viewBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_detail, null, false)
        viewBinding.lifecycleOwner = this
        setContentView(viewBinding.root)

        start()
        observeData()

        viewModel.getLogDisplay("onCreate")
    }

    private fun start() {
        val id = intent.extras?.getString(PRODUCT_ID)
        viewModel.id.value = id
    }

    private fun observeData() {
        // Set up loading.
        viewModel.dataLoading.observe(
            this
        ) { loading -> viewBinding.loading.root.isVisible = loading }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.id.observe(this) { id ->
            viewModel.getProduct()
        }

        viewModel.logLifeCycle.observe(this) { log ->
            viewBinding.tvLog.text = log
        }

        viewModel.product.observe(this) { product ->
            viewBinding.dataItem = viewModel.product.value

            viewBinding.tvRating.rating = product?.rating?.toFloat() ?: 0f

            Glide.with(this)
                .load(product?.images?.firstOrNull())
                .centerInside()
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(viewBinding.ivProduct)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getLogDisplay("onStart")
        //        viewModel.logLifeCycle.value = "onStart"
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLogDisplay("onResume")
//        viewModel.logLifeCycle.value = "onResume"
    }

    override fun onPause() {
        super.onPause()
        viewModel.getLogDisplay("onPause")
//        viewModel.logLifeCycle.value = "onPause"
    }

    override fun onStop() {
        super.onStop()
        viewModel.getLogDisplay("onStop")
//        viewModel.logLifeCycle.value = "onStop"
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.getLogDisplay("onDestroy")
//        viewModel.logLifeCycle.value = "onDestroy"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        private const val PRODUCT_ID = "id"
        fun newIntent(context: Context, id: String) =
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(PRODUCT_ID, id)
            })
    }
}
package com.example.codemobilechallengeapp.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.codemobilechallengeapp.R
import com.example.codemobilechallengeapp.core.entity.Product
import com.example.codemobilechallengeapp.databinding.ActivityHomeBinding
import com.example.codemobilechallengeapp.databinding.ActivityLoginBinding
import com.example.codemobilechallengeapp.presentation.main.adapter.ProductAdapter
import com.example.codemobilechallengeapp.util.SharePreference
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var viewBinding: ActivityHomeBinding

    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_home, null, false)
        viewBinding.lifecycleOwner = this
        setContentView(viewBinding.root)

        viewBinding.viewModel = viewModel

        if (SharePreference(this).token.isNullOrEmpty()) {
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            finish()
        } else {
            start()
            observeData()
        }
    }

    private fun start() {
        viewModel.getProductList()

        productAdapter = ProductAdapter(this)
        viewBinding.recyclerview.apply {
            adapter = productAdapter.apply {
                actionListener = object : ProductAdapter.ActionListener {
                    override fun onItemClicked(item: Product) {
                        DetailActivity.newIntent(this@HomeActivity, item.id ?: "")
                    }
                }
            }
        }

        viewBinding.searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.products.value?.let { list ->
                    val newList = list.filter { it.title?.contains(newText) == true }
                    productAdapter.list = newList
                    productAdapter.notifyDataSetChanged()
                }
                return true
            }
        })

        val filterList = listOf(
            "ทั้งหมด",
            "ราคามากกว่า 1000",
            "ราคารวมต่อชิ้น",
            "เรียงเรตติ้ง",
            "ราคารวมทั้งหมด"
        )
        val spinnerAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, filterList)
        viewBinding.spFilter.apply {
            adapter = spinnerAdapter.apply {
                onItemSelectedListener = object : OnItemSelectedListener,
                    AdapterView.OnItemSelectedListener {
                    override fun onNavigationItemSelected(item: MenuItem): Boolean {

                        return true
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewBinding.tvSum.visibility = View.GONE
                        productAdapter.isDisplayTotalPricePerEach = false

                        when (position) {
                            0 -> {
                                productAdapter.list = viewModel.products.value ?: listOf()
                            }

                            1 -> {
                                viewModel.products.value?.let { list ->
                                    val newList = list.filter { (it.price ?: 0.0) >= 1000.0 }
                                    productAdapter.list = newList
                                }
                            }

                            2 -> {
                                productAdapter.list = viewModel.products.value ?: listOf()
                                productAdapter.isDisplayTotalPricePerEach = true
                            }

                            3 -> {
                                viewModel.products.value?.let { list ->
                                    val newList =
                                        list.sortedByDescending { product -> product.rating }
                                    productAdapter.list = newList
                                }
                            }

                            4 -> {
                                var sum = 0.0
                                viewModel.products.value?.forEach { product ->
                                    sum += (product.price ?: 0.0) * (product.stock ?: 0)
                                }
                                viewBinding.tvSum.visibility = View.VISIBLE
                                viewBinding.tvSum.text = getString(R.string.sum, sum)

                                productAdapter.list = viewModel.products.value ?: listOf()
                            }
                        }

                        productAdapter.notifyDataSetChanged()
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                }
            }
        }
    }

    private fun observeData() {
        // Set up loading.
        viewModel.dataLoading.observe(
            this
        ) { loading -> viewBinding.loading.root.isVisible = loading }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.products.observe(this) { products ->
            productAdapter.list = products
        }
    }

}
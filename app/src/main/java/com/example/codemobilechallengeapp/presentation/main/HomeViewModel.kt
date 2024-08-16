package com.example.codemobilechallengeapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.codemobilechallengeapp.core.entity.Product
import com.example.codemobilechallengeapp.presentation.base.BaseViewModel
import com.example.codemobilechallengeapp.core.remote.model.Result
import com.example.codemobilechallengeapp.core.remote.repository.HomeRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    private val _products = MutableLiveData(mutableListOf<Product>())
    val products = _products

    fun getProductList() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val result = homeRepository.getProductList()) {
                is Result.Success -> {
                    _dataLoading.postValue(false)
                    _products.value = result.data
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _error.postValue(result.exception.message.orEmpty())
                }

                else -> {
                    //Nothing to do
                }
            }
        }
    }

}
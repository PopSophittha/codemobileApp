package com.example.codemobilechallengeapp.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.codemobilechallengeapp.core.entity.Product
import com.example.codemobilechallengeapp.presentation.base.BaseViewModel
import com.example.codemobilechallengeapp.core.remote.model.Result
import com.example.codemobilechallengeapp.core.remote.repository.HomeRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    private val _id = MutableLiveData<String>()
    val id = _id

    private val _product = MutableLiveData<Product?>()
    val product = _product

    private val _logLifeCycle = MutableLiveData("")
    val logLifeCycle = _logLifeCycle

    fun getLogDisplay(log: String){
        _logLifeCycle.value = _logLifeCycle.value.plus(log).plus("\n")
    }

    fun getProduct() {
        id.value?.let { id ->
            viewModelScope.launch {
                _dataLoading.postValue(true)
                when (val result = homeRepository.getProduct(id)) {
                    is Result.Success -> {
                        _dataLoading.postValue(false)
                        _product.value = result.data
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

}
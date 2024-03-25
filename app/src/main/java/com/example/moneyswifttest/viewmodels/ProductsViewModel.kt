package com.example.moneyswifttest.viewmodels

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import com.example.core.Resource
import com.example.domain.models.ProductDomain
import com.example.domain.models.ProductsDomain
import com.example.domain.usecases.ProductsUseCase
import kotlinx.coroutines.flow.onEach


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SpaceNewsState())
    val state: State<SpaceNewsState> = _state

    init {
        getProducts()
    }

    private fun getProducts() {
        productsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SpaceNewsState(products = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = SpaceNewsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SpaceNewsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class SpaceNewsState(
    var isLoading:Boolean=false,
    val products: List<ProductsDomain> = emptyList(),
    val productDetails: ProductDomain? = null,
    var error:String=""
)
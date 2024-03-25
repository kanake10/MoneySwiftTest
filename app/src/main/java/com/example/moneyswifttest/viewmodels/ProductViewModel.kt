package com.example.moneyswifttest.viewmodels

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.mutableStateOf
import com.example.core.Resource
import com.example.domain.usecases.ProductUseCase
import com.example.moneyswifttest.viewmodels.SpaceNewsState
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class ProductViewModel @Inject constructor(
    private  val productUseCase: ProductUseCase
) :ViewModel() {

    private val _state = mutableStateOf(SpaceNewsState())
    val state: State<SpaceNewsState> = _state
    fun getProductDetails(id: Int) {
        productUseCase(id).onEach { data ->
            when(data) {
                is Resource.Success -> {
                    _state.value = SpaceNewsState(productDetails = data.data)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}
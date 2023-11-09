package com.dicoding.warceng.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.warceng.data.MenuRepository
import com.dicoding.warceng.model.Menu
import com.dicoding.warceng.model.OrderMenu
import com.dicoding.warceng.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MenuRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderMenu>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderMenu>>
        get() = _uiState

    fun getMenuById(menuId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderMenuById(menuId))
        }
    }

    fun addToCart(menu: Menu, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMenu(menu.id, count)
        }
    }
}
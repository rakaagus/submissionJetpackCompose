package com.dicoding.warceng.ui.screen.category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.warceng.data.MenuRepository
import com.dicoding.warceng.model.OrderMenu
import com.dicoding.warceng.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: MenuRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderMenu>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderMenu>>>
        get() = _uiState

    fun getMenuByCategory(category: String){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAllMenuByCategory(category)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{orderMenu->
                    _uiState.value = UiState.Success(orderMenu)
                }
        }
    }
}
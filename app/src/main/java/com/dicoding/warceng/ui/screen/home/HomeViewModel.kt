package com.dicoding.warceng.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.warceng.data.MenuRepository
import com.dicoding.warceng.model.Menu
import com.dicoding.warceng.model.OrderMenu
import com.dicoding.warceng.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MenuRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderMenu>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderMenu>>>
        get() = _uiState

    fun getAllMenu() {
        viewModelScope.launch {
            repository.getAllMenu()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{ orderMenu->
                    _uiState.value = UiState.Success(orderMenu)
                }
        }
    }

}
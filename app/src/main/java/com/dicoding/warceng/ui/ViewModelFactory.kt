package com.dicoding.warceng.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.warceng.data.MenuRepository
import com.dicoding.warceng.ui.screen.cart.CartViewModel
import com.dicoding.warceng.ui.screen.category.CategoryViewModel
import com.dicoding.warceng.ui.screen.detail.DetailViewModel
import com.dicoding.warceng.ui.screen.home.HomeViewModel

class ViewModelFactory(val repository: MenuRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(CartViewModel::class.java)){
            return CartViewModel(repository) as T
        }else if(modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            return CategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
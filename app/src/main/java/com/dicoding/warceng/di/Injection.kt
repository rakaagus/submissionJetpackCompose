package com.dicoding.warceng.di

import com.dicoding.warceng.data.MenuRepository

object Injection {
    fun provideRepository(): MenuRepository{
        return MenuRepository.getInstance()
    }
}
package com.dicoding.warceng.model

data class Menu(
    val id: Long,
    val image: Int,
    val type: String,
    val desc: String,
    val title: String,
    val price: Int
)
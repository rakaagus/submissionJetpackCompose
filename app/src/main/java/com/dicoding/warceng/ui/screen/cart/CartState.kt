package com.dicoding.warceng.ui.screen.cart

import com.dicoding.warceng.model.OrderMenu

data class CartState(
    val orderMenu: List<OrderMenu>,
    val totalPrice: Int
)

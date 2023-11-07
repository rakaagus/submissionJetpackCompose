package com.dicoding.warceng.data

import com.dicoding.warceng.model.FakeData
import com.dicoding.warceng.model.OrderMenu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MenuRepository {

    private val orderMenu = mutableListOf<OrderMenu>()

    init {
        if (orderMenu.isEmpty()) {
            FakeData.dummyMenu.forEach {
                orderMenu.add(OrderMenu(it, 0))
            }
        }
    }

    fun getAllMenu(): Flow<List<OrderMenu>> {
        return flowOf(orderMenu)
    }

    companion object {
        @Volatile
        private var instance: MenuRepository? = null

        fun getInstance(): MenuRepository =
            instance ?: synchronized(this) {
                MenuRepository().apply {
                    instance = this
                }
            }
    }
}
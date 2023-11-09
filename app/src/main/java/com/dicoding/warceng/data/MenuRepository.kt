package com.dicoding.warceng.data

import com.dicoding.warceng.model.FakeData
import com.dicoding.warceng.model.OrderMenu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

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

    fun getAllMenuByCategory(category: String): Flow<List<OrderMenu>>{
        return getAllMenu().map {orderMenu->
            orderMenu.filter { orderMenu ->
                orderMenu.menu.type == category
            }
        }
    }

    fun getOrderMenuById(menuId: Long): OrderMenu {
        return orderMenu.first {
            it.menu.id == menuId
        }
    }

    fun updateOrderMenu(menuId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderMenu.indexOfFirst { it.menu.id == menuId }
        val result = if (index >= 0) {
            val orderMenu = orderMenu[index]
            this.orderMenu[index] =
                orderMenu.copy(menu = orderMenu.menu, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderMenu(): Flow<List<OrderMenu>> {
        return getAllMenu()
            .map { orderMenu ->
                orderMenu.filter { orderMenu ->
                    orderMenu.count != 0
                }
            }
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
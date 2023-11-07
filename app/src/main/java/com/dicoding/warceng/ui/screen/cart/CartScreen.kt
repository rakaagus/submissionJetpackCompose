package com.dicoding.warceng.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.warceng.R
import com.dicoding.warceng.di.Injection
import com.dicoding.warceng.ui.ViewModelFactory
import com.dicoding.warceng.ui.common.UiState
import com.dicoding.warceng.ui.components.OrderButton
import com.dicoding.warceng.ui.components.OrderMenuItem

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    onOrderButtonClicked: (String) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderMenu()
            }
            is UiState.Success -> {
                CartContent(
                    state = uiState.data,
                    onProductCountChanged = { menuId, count ->
                        viewModel.updateOrderReward(menuId, count) },
                    onOrderButtonClick = onOrderButtonClicked
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    state: CartState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val shareMessage = stringResource(
        id = R.string.share_message,
        state.orderMenu.count(),
        state.totalPrice
    )
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.menu_cart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(weight = 1f)
        ){
            items(state.orderMenu, key = {it.menu.id}){item->
                OrderMenuItem(
                    menuId = item.menu.id,
                    image = item.menu.image,
                    title = item.menu.title,
                    price = item.menu.price,
                    count = item.menu.price * item.count,
                    onProductCountChanged = onProductCountChanged
                )
            }
        }
        OrderButton(
            text = "Order",
            enable = state.orderMenu.isNotEmpty(),
            onClick = {
                onOrderButtonClick(shareMessage)
            },
            modifier = Modifier.padding(24.dp)
        )
    }
}
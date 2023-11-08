package com.dicoding.warceng.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.warceng.R
import com.dicoding.warceng.di.Injection
import com.dicoding.warceng.ui.ViewModelFactory
import com.dicoding.warceng.ui.common.UiState
import com.dicoding.warceng.ui.components.OrderButton
import com.dicoding.warceng.ui.components.ProductCounter
import com.dicoding.warceng.ui.components.SectionText
import com.dicoding.warceng.ui.theme.WarcengAppTheme
import com.dicoding.warceng.ui.theme.coffeeColor

@Composable
fun DetailScreen(
    menuId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMenuById(menuId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    image = data.menu.image,
                    title = data.menu.title,
                    desc = data.menu.desc,
                    basePrice = data.menu.price,
                    count = data.count,
                    onBackClick = navigateBack,
                    onAddToCart = {count ->
                        viewModel.addToCart(data.menu, count)
                        navigateToCart()
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    desc: String,
    basePrice: Int,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var totalPrice by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.screen_detail),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            )
        )
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(horizontal = 24.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 15.dp),
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                )
                Text(
                    text = stringResource(id = R.string.menu_price, basePrice),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Color.Black
                )
            }
            SectionText(title = "Product Info", modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = desc,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    ),
                )
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.LightGray))
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Column {
                totalPrice = basePrice * orderCount
                Text(
                    text = stringResource(id = R.string.menu_price, totalPrice),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = coffeeColor,
                )
                ProductCounter(
                    orderId = 1,
                    orderCount = orderCount,
                    onProductIncreased = { orderCount++ },
                    onProductDecreased = { if(orderCount > 0) orderCount-- }
                )
            }
            Spacer(modifier = Modifier.width(30.dp))
            OrderButton(
                text = "Order Now",
                enable = orderCount > 0,
                onClick = {
                    onAddToCart(orderCount)
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPrev() {
    WarcengAppTheme {
        DetailContent(
            R.drawable.menu_1,
            "Jaket Hoodie Dicoding",
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id es",
            15,
            1,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}
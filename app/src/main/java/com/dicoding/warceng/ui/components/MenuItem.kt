package com.dicoding.warceng.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.warceng.R
import com.dicoding.warceng.ui.theme.WarcengAppTheme
import com.dicoding.warceng.ui.theme.coffeeColor

@Composable
fun MenuItem(
    image: Int,
    title: String,
    price: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(120.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, coffeeColor),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        start = 10.dp,
                        end = 10.dp
                    )
                    .fillMaxWidth()
                    .height(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = stringResource(id = R.string.menu_price, price),
                    style = MaterialTheme.typography.titleSmall,
                    color = coffeeColor
                )
                OutlinedButton(
                    onClick = {},
                    border = BorderStroke(1.dp, coffeeColor),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 5.dp
                    ),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Add To Cart",
                        style = MaterialTheme.typography.titleSmall,
                        color = coffeeColor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MenuItemPrev() {
    WarcengAppTheme {
        MenuItem(
            R.drawable.menu_1,
            "Coffee Mantan",
            5,
        )
    }
}
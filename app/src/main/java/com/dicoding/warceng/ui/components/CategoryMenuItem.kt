package com.dicoding.warceng.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.warceng.R
import com.dicoding.warceng.ui.theme.LightGray
import com.dicoding.warceng.ui.theme.WarcengAppTheme
import com.dicoding.warceng.ui.theme.coffeeColor

@Composable
fun CategoryMenuItem(
    image: Int,
    title: String,
    price: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGray
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(
                        horizontal = 10.dp,
                        vertical = 10.dp
                    )
                    .size(90.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .weight(1.0f)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Text(
                text = stringResource(id = R.string.menu_price, price),
                color = coffeeColor,
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(
                        end = 20.dp
                    )
            )
        }
    }
}

@Preview
@Composable
fun CategoryMenuItem() {
    WarcengAppTheme {
        CategoryMenuItem(
            R.drawable.menu_6, "Coffee Mantan", 5
        )
    }
}
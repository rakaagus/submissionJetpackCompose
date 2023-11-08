package com.dicoding.warceng.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.warceng.R
import com.dicoding.warceng.ui.theme.LightGray
import com.dicoding.warceng.ui.theme.WarcengAppTheme

@Composable
fun CategoriesItem(
    image: Int,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGray
        ),
        modifier = modifier.width(180.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(
                        end = 5.dp
                    )
                    .size(
                        width = 55.dp,
                        height = 50.dp
                    )
                    .clip(RoundedCornerShape(
                        topStart = 10.dp,
                        bottomStart = 10.dp
                    ))
            )
            Text(
                text = title,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                color = Color.Black,
            )
        }
    }
}

@Preview
@Composable
fun CategoriesItemPrev() {
    WarcengAppTheme {
        CategoriesItem(
            R.drawable.menu_1,
            "FOOD",
            Modifier.padding(10.dp)
        )
    }
}
package com.dicoding.warceng.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.warceng.ui.theme.SubmissionJetpackComposeTheme
import com.dicoding.warceng.ui.theme.coffeeColor

@Composable
fun OrderButton(
    text: String,
    enable: Boolean = true,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        enabled = enable,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = coffeeColor
        ),
        modifier = modifier.fillMaxWidth().height(47.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun OrderButtonPrev() {
    SubmissionJetpackComposeTheme {
        OrderButton(
            text = "Order",
            onClick = {}
        )
    }
}
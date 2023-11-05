package com.dicoding.warceng.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.warceng.ui.theme.SubmissionJetpackComposeTheme

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 15.dp)
    )
}

@Preview
@Composable
fun SectionTextPrev() {
    SubmissionJetpackComposeTheme {
        SectionText("Contoh Gasih")
    }
}
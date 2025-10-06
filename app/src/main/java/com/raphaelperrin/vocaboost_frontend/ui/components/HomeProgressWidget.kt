package com.raphaelperrin.vocaboost_frontend.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raphaelperrin.vocaboost_frontend.ui.theme.orange
import com.raphaelperrin.vocaboost_frontend.ui.theme.white

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeProgressWidget(
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.white,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(16.dp),
        )
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocalFireDepartment,
                    contentDescription = "Streak icon",
                    tint = MaterialTheme.colorScheme.orange,
                    modifier = Modifier.size(38.dp)
                )
                Text(
                    text = "7 days",
                    style = TextStyle(
                        fontWeight = FontWeight.W900,
                        fontSize = 28.sp
                    ),
                    color = MaterialTheme.colorScheme.orange,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Progression globale :",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.size(8.dp))

                LinearWavyProgressIndicator(
                    progress = { 0.4f },
                    modifier = Modifier
                        .weight(1f)
                )

                Spacer(modifier = Modifier.size(8.dp))

                Text(
                    text = "40%",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .widthIn(min = 30.dp)
                )
            }
        }

    }
}
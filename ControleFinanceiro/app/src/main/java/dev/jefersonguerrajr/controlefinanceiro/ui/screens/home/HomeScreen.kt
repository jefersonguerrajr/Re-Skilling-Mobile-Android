package dev.jefersonguerrajr.controlefinanceiro.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onNavigateToRevenueExpenses: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = "Olá, Fulano, bem vindo ao aplicativo de despesas pessoais.",
            style = MaterialTheme.typography.titleLarge)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Saldo: R$ 1000,00")
            Button(onClick = onNavigateToRevenueExpenses) {
                Text(text = "Adicionar ganhos")
            }
        }

        Button(onClick = onNavigateToRevenueExpenses, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Adicionar despesas")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(onNavigateToRevenueExpenses = {})
}

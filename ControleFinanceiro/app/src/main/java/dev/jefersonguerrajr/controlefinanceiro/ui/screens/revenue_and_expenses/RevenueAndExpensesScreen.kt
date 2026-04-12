package dev.jefersonguerrajr.controlefinanceiro.ui.screens.revenue_and_expenses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jefersonguerrajr.controlefinanceiro.data.model.Transaction
import dev.jefersonguerrajr.controlefinanceiro.ui.FinanceViewModel

@Composable
fun RevenueAndExpensesScreen(
    type: String? = "revenue",
    viewModel: FinanceViewModel,
    onBack: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Adicione aqui seus ${if (type == "revenue") "ganhos" else "despesas"}",
            style = MaterialTheme.typography.titleLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            label = {
                Text("Título", color = MaterialTheme.colorScheme.onSurface)
            }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = { value = it },
            label = {
                Text("Valor (R$)")
            }
        )

        Button(
            onClick = {
                val amount = value.toDoubleOrNull() ?: 0.0
                val transaction = Transaction(
                    title = name,
                    description = description,
                    amount = amount,
                    type = type ?: "revenue"
                )
                viewModel.addTransaction(transaction)
                onBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Adicionar")
        }

    }
}

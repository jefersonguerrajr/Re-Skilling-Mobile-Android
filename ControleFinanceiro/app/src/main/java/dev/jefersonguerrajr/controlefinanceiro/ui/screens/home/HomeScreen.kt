package dev.jefersonguerrajr.controlefinanceiro.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jefersonguerrajr.controlefinanceiro.ui.FinanceViewModel

@Composable
fun HomeScreen(
    viewModel: FinanceViewModel,
    onNavigateToRevenueExpenses: (String) -> Unit
) {
    val transactions by viewModel.transactions.collectAsState()
    val userName by viewModel.userName.collectAsState()
    val balance = viewModel.getTotalBalance()

    var showDialog by remember { mutableStateOf(userName.isEmpty()) }
    var tempName by remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { /* Não permite fechar sem nome se desejar */ },
            title = { Text(text = "Bem-vindo!") },
            text = {
                Column {
                    Text(text = "Como gostaria de ser chamado?")
                    TextField(
                        value = tempName,
                        onValueChange = { tempName = it },
                        label = { Text("Seu nome") },
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (tempName.isNotBlank()) {
                            viewModel.setUserName(tempName)
                            showDialog = false
                        }
                    }
                ) {
                    Text("Salvar")
                }
            }
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Olá, ${userName.ifBlank { "Fulano" }}, bem vindo ao aplicativo de despesas pessoais.",
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Saldo: R$ %.2f".format(balance))
            Button(onClick = { onNavigateToRevenueExpenses("revenue") }) {
                Text(text = "Adicionar ganhos")
            }
        }

        Button(
            onClick = { onNavigateToRevenueExpenses("expenses") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Adicionar despesas")
        }

        Text(text = "Últimas Transações:", style = MaterialTheme.typography.titleMedium)
        transactions.forEach { transaction ->
            Text(text = "${transaction.title}: R$ ${transaction.amount} (${transaction.type})")
        }
    }
}

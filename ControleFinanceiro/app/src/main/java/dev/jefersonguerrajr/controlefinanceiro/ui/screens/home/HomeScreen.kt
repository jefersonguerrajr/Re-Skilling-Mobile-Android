package dev.jefersonguerrajr.controlefinanceiro.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
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
    val goal by viewModel.goal.collectAsState()
    val balance = viewModel.getTotalBalance()

    var showUserDialog by remember { mutableStateOf(userName.isEmpty()) }
    var showGoalDialog by remember { mutableStateOf(false) }
    var tempName by remember { mutableStateOf("") }

    // Dialog para o nome do usuário
    if (showUserDialog) {
        AlertDialog(
            onDismissRequest = { },
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
                            showUserDialog = false
                        }
                    }
                ) {
                    Text("Salvar")
                }
            }
        )
    }

    // Dialog para criar meta
    if (showGoalDialog) {
        var goalName by remember { mutableStateOf("") }
        var goalTarget by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showGoalDialog = false },
            title = { Text(text = "Criar nova meta") },
            text = {
                Column {
                    TextField(
                        value = goalName,
                        onValueChange = { goalName = it },
                        label = { Text("Nome da meta") }
                    )
                    TextField(
                        value = goalTarget,
                        onValueChange = { goalTarget = it },
                        label = { Text("Valor alvo") },
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val target = goalTarget.toDoubleOrNull() ?: 0.0
                        if (goalName.isNotBlank() && target > 0) {
                            viewModel.setGoal(goalName, target)
                            showGoalDialog = false
                        }
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { showGoalDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)) {
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

        // Seção da Meta
        if (goal == null) {
            Button(
                onClick = { showGoalDialog = true },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Criar meta")
            }
        } else {
            val progress = (balance / goal!!.target).coerceIn(0.0, 1.0).toFloat()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(text = "Meta: ${goal!!.name}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Progresso: R$ %.2f / R$ %.2f".format(balance, goal!!.target))
                
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
                
                Text(
                    text = if (balance >= goal!!.target) 
                        "Meta atingida! Parabéns!" 
                    else 
                        "Faltam R$ %.2f para atingir sua meta".format(goal!!.target - balance),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

package dev.jefersonguerrajr.controlefinanceiro.ui

import androidx.lifecycle.ViewModel
import dev.jefersonguerrajr.controlefinanceiro.data.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Goal(val name: String, val target: Double)

class FinanceViewModel : ViewModel() {
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    private val _goal = MutableStateFlow<Goal?>(null)
    val goal: StateFlow<Goal?> = _goal.asStateFlow()

    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setGoal(name: String, target: Double) {
        _goal.value = Goal(name, target)
    }

    fun addTransaction(transaction: Transaction) {
        _transactions.update { currentList ->
            currentList + transaction
        }
    }

    fun getTotalBalance(): Double {
        return _transactions.value.sumOf { 
            if (it.type == "revenue") it.amount else -it.amount 
        }
    }
}

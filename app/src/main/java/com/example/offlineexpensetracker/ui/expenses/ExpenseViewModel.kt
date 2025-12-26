package com.example.offlineexpensetracker.ui.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.offlineexpensetracker.data.ExpenseRepository
import com.example.offlineexpensetracker.data.local.ExpenseEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ExpenseViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _expenses = MutableStateFlow<List<ExpenseEntity>>(emptyList())
    val expenses: StateFlow<List<ExpenseEntity>> = _expenses.asStateFlow()

    init {
        observeExpenses()
    }

    private fun observeExpenses() {
        viewModelScope.launch {
            repository.getExpenses().collect { list ->
                _expenses.value = list
            }
        }
    }

    fun addExpense(
        amount: Double,
        category: String,
        note: String?
    ) {
        viewModelScope.launch {
            repository.addExpense(
                ExpenseEntity(
                    title = category,                 // ✅ FIX
                    amount = amount,
                    category = category,
                    note = note,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }


    fun getMonthlyTotals(): Map<String, Double> {
        return runBlocking {
            repository.getMonthlyTotals()
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearExpenses()
        }
    }
}

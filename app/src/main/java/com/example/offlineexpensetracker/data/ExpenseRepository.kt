package com.example.offlineexpensetracker.data

import com.example.offlineexpensetracker.data.local.ExpenseDao
import com.example.offlineexpensetracker.data.local.ExpenseEntity
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class ExpenseRepository(
    private val expenseDao: ExpenseDao
) {



    fun getExpenses(): Flow<List<ExpenseEntity>> {
        return expenseDao.getAllExpenses()
    }

    suspend fun clearExpenses() {
        expenseDao.clearAll()
    }
    suspend fun addExpense(expense: ExpenseEntity) {
        expenseDao.insertExpense(expense)
    }

    suspend fun getMonthlyTotals(): Map<String, Double> {
        val expenses = expenseDao.getAllExpensesOnce() // We'll create this next
        val calendar = Calendar.getInstance()
        val monthlyTotals = mutableMapOf<String, Double>()

        for (expense in expenses) {
            calendar.timeInMillis = expense.timestamp
            val monthKey = "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH)+1}" // e.g., "2025-12"
            monthlyTotals[monthKey] = (monthlyTotals[monthKey] ?: 0.0) + expense.amount
        }

        return monthlyTotals
    }
}

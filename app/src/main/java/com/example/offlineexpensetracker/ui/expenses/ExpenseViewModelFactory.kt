package com.example.offlineexpensetracker.ui.expenses

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.offlineexpensetracker.data.ExpenseRepository
import com.example.offlineexpensetracker.data.local.DatabaseProvider

class ExpenseViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val db = DatabaseProvider.getDatabase(context)
        val repo = ExpenseRepository(db.expenseDao())

        return ExpenseViewModel(repo) as T
    }
}

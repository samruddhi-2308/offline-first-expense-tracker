package com.example.offlineexpensetracker.ui.expenses
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.offlineexpensetracker.data.local.ExpenseEntity

@Composable
fun ExpenseScreen(
    context: Context,
    viewModel: ExpenseViewModel = viewModel(
        factory = ExpenseViewModelFactory(context)
    )
) {
    val expenses by viewModel.expenses.collectAsState()

    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") }
        )

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") }
        )

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Note (optional)") }
        )

        Button(
            onClick = {
                val amt = amount.toDoubleOrNull()
                if (amt != null && category.isNotBlank()) {
                    viewModel.addExpense(
                        amount = amt,
                        category = category,
                        note = note.ifBlank { null }
                    )
                    amount = ""
                    category = ""
                    note = ""
                }
            }
        ) {
            Text("Add Expense")
        }

        Divider()

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(expenses) { expense: ExpenseEntity ->
                Text("${expense.category} — ₹${expense.amount}")
            }
        }
    }
}

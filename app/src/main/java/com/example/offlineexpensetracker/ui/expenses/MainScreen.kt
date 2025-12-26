package com.example.offlineexpensetracker.ui.expenses

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    context: Context,
    viewModel: ExpenseViewModel = viewModel(
        factory = ExpenseViewModelFactory(context)
    )
) {
    val expenses by viewModel.expenses.collectAsState()
    var monthlyTotals by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Compute monthly totals whenever expenses change
    LaunchedEffect(expenses) {
        scope.launch {
            monthlyTotals = viewModel.getMonthlyTotals()
        }
    }

    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text("Add Expense", style = MaterialTheme.typography.titleLarge)

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

                    when {
                        amt == null || amt <= 0.0 -> {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Enter a valid positive amount"
                                )
                            }
                        }

                        category.isBlank() -> {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Category is required"
                                )
                            }
                        }

                        else -> {
                            viewModel.addExpense(
                                amount = amt,
                                category = category.trim(),
                                note = note.ifBlank { null }
                            )

                            amount = ""
                            category = ""
                            note = ""

                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Expense added"
                                )
                            }
                        }
                    }
                }
            ) {
                Text("Add Expense")
            }

            Divider()

            Text("Expenses", style = MaterialTheme.typography.titleLarge)

            if (expenses.isEmpty()) {
                Text(
                    "No expenses yet. Start tracking.",
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(expenses) { expense ->
                        Text("${expense.category} — ₹${expense.amount}")
                    }
                }
            }

            Divider()

            Text("Monthly Summary", style = MaterialTheme.typography.titleLarge)

            if (monthlyTotals.isEmpty()) {
                Text("No summary available yet.")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(monthlyTotals.entries.toList()) { entry ->
                        Text("${entry.key} → ₹${entry.value}")
                    }
                }
            }
        }
    }
}

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
fun MonthlySummaryScreen(
    context: Context,
    viewModel: ExpenseViewModel = viewModel(
        factory = ExpenseViewModelFactory(context)
    )
) {
    var monthlyTotals by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            monthlyTotals = viewModel.getMonthlyTotals()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Monthly Expense Summary", style = MaterialTheme.typography.titleLarge)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(monthlyTotals.entries.toList()) { entry ->
                Text("${entry.key} → ₹${entry.value}")
            }
        }
    }
}

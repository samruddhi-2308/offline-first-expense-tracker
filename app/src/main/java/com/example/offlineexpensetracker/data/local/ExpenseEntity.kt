package com.example.offlineexpensetracker.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val amount: Double,

    val category: String,

    val timestamp: Long,   // epoch millis for sorting + monthly trends

    val note: String? = null
)



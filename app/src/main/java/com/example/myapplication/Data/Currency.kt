package com.example.myapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class Currency(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val firstCurrencyName: String,
    val firstCurrencyAmount: Double,
    val secondCurrencyName: String,
    val secondCurrencyAmount: Double
)
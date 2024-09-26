package com.example.myapplication.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyDao {
    @Insert
    suspend fun insert(currency: Currency)

    @Query("SELECT * FROM currency_table")
    suspend fun getAllCurrencies(): List<Currency>

    @Query("DELETE FROM currency_table WHERE id = :currencyId")
    suspend fun deleteCurrency(currencyId: Long)
}
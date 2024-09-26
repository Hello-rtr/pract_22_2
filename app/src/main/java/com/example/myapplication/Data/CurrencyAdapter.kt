package com.example.myapplication.Data

class CurrencyAdapter(private val dao: CurrencyDao)  {
        suspend fun insert(currency: Currency) {
            dao.insert(currency)
        }

        suspend fun getAllCurrencies(): List<Currency> {
            return dao.getAllCurrencies()
        }

        suspend fun deleteCurrency(id: Long) {
            dao.deleteCurrency(id)
        }
    }
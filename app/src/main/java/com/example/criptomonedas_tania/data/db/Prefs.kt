package com.example.criptomonedas_tania.data.db

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {

    private val PREFS_NAME = "com.example.criptomonedas_tania"
    private val SHARED_ID_CRIPTO = "shared.id_criptomoneda"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var idCripto: String?
        get() = prefs.getString(SHARED_ID_CRIPTO, "")
        set(value) = prefs.edit().putString(SHARED_ID_CRIPTO, value!!).apply()



}
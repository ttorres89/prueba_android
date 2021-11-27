package com.example.criptomonedas_tania.data.db.config

import android.app.Application
import androidx.room.Room
import com.example.criptomonedas_tania.data.db.Prefs
import com.example.criptomonedas_tania.data.db.config.criptomoneda_app.DB

class Criptomoneda_app: Application() {

    companion object{
        lateinit var db: DB
        lateinit var prefs: Prefs
    }

    override fun onCreate(){
        super.onCreate()
        db = Room.databaseBuilder(this, DB::class.java, "dbcriptomoneda").build()

        prefs = Prefs(applicationContext)
    }
}
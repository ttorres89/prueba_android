package com.example.criptomonedas_tania.data.db.config.criptomoneda_app

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.criptomonedas_tania.data.db.dao.CriptomonedaDao
import com.example.criptomonedas_tania.data.db.entities.CriptoMonDetailEntity
import com.example.criptomonedas_tania.data.db.entities.CriptomonedaEntity

@Database(
    entities = [CriptomonedaEntity::class, CriptoMonDetailEntity::class],
    version = 2,
    exportSchema = false)
abstract class DB: RoomDatabase() {
    abstract fun criptomonedaDao(): CriptomonedaDao
}
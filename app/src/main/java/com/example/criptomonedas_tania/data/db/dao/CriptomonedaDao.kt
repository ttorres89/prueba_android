package com.example.criptomonedas_tania.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.criptomonedas_tania.data.db.entities.CriptoMonDetailEntity
import com.example.criptomonedas_tania.data.db.entities.CriptomonedaEntity
import com.example.criptomonedas_tania.data.model.Criptomoneda
import org.jetbrains.annotations.NotNull

@Dao
interface CriptomonedaDao {

    @Query("SELECT * from criptomoneda")
    suspend fun getAll(): List<CriptomonedaEntity>

    @Query("SELECT * from criptomoneda_detail where criptomoneda_detail.id=:id")
    suspend fun getCriptomoneda(@NotNull id: String): CriptoMonDetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll (@NotNull criptomoneda: List<CriptomonedaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCriptomoneda (@NotNull criptomoneda_detail: CriptoMonDetailEntity)

}
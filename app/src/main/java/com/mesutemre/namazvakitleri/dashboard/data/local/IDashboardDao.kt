package com.mesutemre.namazvakitleri.dashboard.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.VakitInfoEntity

@Dao
interface IDashboardDao {

    @Query("SELECT * FROM VakitInfoEntity WHERE miladiTarih=:tarih")
    suspend fun getVakitInfo(tarih: String): VakitInfoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVakitInfo(vararg vakitInfoEntity: VakitInfoEntity)
}
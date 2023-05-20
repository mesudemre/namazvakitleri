package com.mesutemre.namazvakitleri.dashboard.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.TarihteBugunEntity
import com.mesutemre.namazvakitleri.dashboard.data.local.entity.VakitInfoEntity

@Dao
interface IDashboardDao {

    @Query("SELECT * FROM VakitInfoEntity WHERE miladiTarih=:tarih")
    suspend fun getVakitInfo(tarih: String): VakitInfoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveVakitInfo(vararg vakitInfoEntity: VakitInfoEntity)

    @Query("SELECT * FROM VakitInfoEntity WHERE miladiTarih=:bugun OR miladiTarih=:yarin")
    suspend fun getVakitBugunYarin(bugun: String, yarin: String): List<VakitInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTarihteBugunList(vararg tarihteBugunEntity: TarihteBugunEntity)

    @Query("DELETE FROM TarihteBugunEntity")
    suspend fun clearTarihteBugun()

    @Query("SELECT * FROM TarihteBugunEntity")
    suspend fun getTarihteBugunList(): List<TarihteBugunEntity>
}
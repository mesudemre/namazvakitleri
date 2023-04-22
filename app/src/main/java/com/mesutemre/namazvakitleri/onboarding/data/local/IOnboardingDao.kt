package com.mesutemre.namazvakitleri.onboarding.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.CityEntity
import com.mesutemre.namazvakitleri.onboarding.data.local.entity.DistrictEntity

@Dao
interface IOnboardingDao {

    @Query("SELECT * FROM CityEntity")
    suspend fun getCityList(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCity(vararg cityEntity: CityEntity)

    @Query("SELECT c.*,d.* FROM DistrictEntity d JOIN CityEntity c ON d.ilId=c.sehirId WHERE d.ilId=:cityId")
    suspend fun getDistrictListByCityId(cityId: Int): Map<CityEntity, List<DistrictEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDistrict(vararg districtEntity: DistrictEntity)

    @Query("SELECT EXISTS(SELECT * FROM DistrictEntity WHERE ilId=:cityId)")
    suspend fun isDistrictListSavedBefore(cityId: Int): Boolean
}
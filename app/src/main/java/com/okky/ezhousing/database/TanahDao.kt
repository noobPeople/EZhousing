package com.okky.ezhousing.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TanahDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(tanah: Tanah)

    @Update
    suspend fun update(tanah: Tanah)

    @Query("DELETE FROM tanah WHERE is_keranjang = 0")
    suspend fun deleteAll()

    @Query("SELECT * FROM tanah WHERE is_keranjang = 1")
    fun getKeranjangTanah(): LiveData<List<Tanah>>

    @Query("SELECT EXISTS(SELECT * FROM tanah WHERE name = :name AND is_keranjang = 1)")
    suspend fun isTanahKeranjang(name: String): Boolean
}
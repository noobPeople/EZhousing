package com.okky.ezhousing.database

import androidx.room.*

@Dao
interface TanahDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(tanah: Tanah)

    @Update
    fun update(tanah: Tanah)

//    @Query("DELETE FROM tanah WHERE is_keranjang = 0")
}
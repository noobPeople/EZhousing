package com.okky.ezhousing.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tanah")
data class Tanah (
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = "",

    @ColumnInfo(name = "photo")
    var photo: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "lon")
    var lon: Double,

    @ColumnInfo(name = "lat")
    var lat: Double,

    @ColumnInfo(name = "is_keranjang")
    var isKeranjang: Boolean
    )
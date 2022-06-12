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
    var photo: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "lon")
    var lon: Double,

    @ColumnInfo(name = "lat")
    var lat: Double,

    @ColumnInfo(name = "created_at")
    var created_at: String,

    @field:ColumnInfo(name = "is_keranjang")
    var isKeranjang: Boolean = false

    )
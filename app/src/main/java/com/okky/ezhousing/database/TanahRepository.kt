package com.okky.ezhousing.database

import androidx.lifecycle.LiveData
import com.okky.ezhousing.ui.home.DetailTanahFragment

class TanahRepository (
    private val tanahDao: TanahDao
) {
//    lateinit var text: String

    fun getKeranjangTanah(): LiveData<List<Tanah>> {
        return tanahDao.getKeranjangTanah()
    }

    suspend fun isKeranjangTanah(name: String) : Boolean {
        return tanahDao.isTanahKeranjang(name)
    }

    suspend fun setTanahKeranjang(tanah: Tanah, keranjangState: Boolean) {
        val keranjang = tanahDao.isTanahKeranjang(tanah.name)
        if (keranjang) {
            tanah.isKeranjang = false
            tanahDao.update(tanah)
            tanahDao.deleteAll()
//            text = "Tanah dihapus dari keranjang"
        } else if (!keranjang || keranjang == null) {
            tanah.isKeranjang = true
            tanahDao.insert(tanah)
//            text = "Tanah ditambahkan ke keranjang"
        }
    }

    suspend fun updateTanahKeranjang(tanah: Tanah, keranjangState: Boolean) {
        tanah.isKeranjang = keranjangState
        tanahDao.update(tanah)
        tanahDao.deleteAll()
    }

    companion object {
        @Volatile
        private var instance: TanahRepository? = null
        fun getInstance(usersDao: TanahDao): TanahRepository = instance ?: synchronized(this) {
            instance ?: TanahRepository(usersDao)
        }.also { instance = it }
    }
}
package com.okky.ezhousing.di

import androidx.fragment.app.FragmentActivity
import com.okky.ezhousing.database.TanahDatabase
import com.okky.ezhousing.database.TanahRepository

object Injection {
    fun provideRepository(context: FragmentActivity) : TanahRepository {
//        val apiService = ApiConfig.getApiService()
        val database = TanahDatabase.getInstance(context)
        val dao = database.tanahDao()
        return TanahRepository.getInstance(dao)
    }
}
package com.okky.ezhousing.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okky.ezhousing.database.Tanah
import com.okky.ezhousing.database.TanahRepository
import kotlinx.coroutines.launch

class NotificationsViewModel(private val tanahRepository: TanahRepository): ViewModel() {
    private val _isusers = MutableLiveData<Boolean>()
    val isusers: LiveData<Boolean> = _isusers

    fun getKeranjangTanah() = tanahRepository.getKeranjangTanah()

    fun isKeranjangTanah(name: String) {
        viewModelScope.launch {
            _isusers.value = tanahRepository.isKeranjangTanah(name)
        }
    }

    fun saveTanah(users: Tanah) {
        viewModelScope.launch {
            tanahRepository.setTanahKeranjang(users, true)
        }
    }

    fun deleteTanah(users: Tanah) {
        viewModelScope.launch {
            tanahRepository.updateTanahKeranjang(users, false)
        }
    }
}
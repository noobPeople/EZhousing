package com.okky.ezhousing.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okky.ezhousing.api.ApiConfig
import com.okky.ezhousing.api.response.ListStoryItem
import com.okky.ezhousing.api.response.TanahResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _story = MutableLiveData<List<ListStoryItem>>()
    val story: LiveData<List<ListStoryItem>> = _story

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun setStoryData(token: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getAllTanahWithLocation("Bearer $token")
        client.enqueue(object : Callback<TanahResponse> {
            override fun onResponse(call: Call<TanahResponse>, response: Response<TanahResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _isLoading.value = false
                        _story.value = responseBody.listStory
                    }
                } else {
                    _isLoading.value = false
                    Log.e("MAIN ACTIVITY", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TanahResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("MAIN ACTIVITY", "onFailure: ${t.message}")
            }
        })
    }
}
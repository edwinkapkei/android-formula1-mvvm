package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.data.util.RequestState
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentScheduleViewModel(
    private val application: Application,
    private val getCurrentScheduleUseCase: GetCurrentScheduleUseCase
) : AndroidViewModel(application) {
    val currentSchedule: MutableLiveData<RequestState<ScheduleResponse>> = MutableLiveData()

    fun getCurrentSchedule() = viewModelScope.launch(Dispatchers.IO) {
        currentSchedule.postValue(RequestState.Loading())
        try {
            if (isNetworkAvailable(application)) {
                val apiResult = getCurrentScheduleUseCase.execute()
                currentSchedule.postValue(apiResult)
            } else {
                currentSchedule.postValue(RequestState.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            currentSchedule.postValue(RequestState.Error(e.message.toString()))
        }
    }

    //TODO: create standalone file for checking and inject
    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false

    }
}
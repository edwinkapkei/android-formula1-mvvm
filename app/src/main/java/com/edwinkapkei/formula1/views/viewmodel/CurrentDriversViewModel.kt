package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.data.util.RequestState
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriversUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentDriversViewModel(
    private val application: Application,
    private val getCurrentDriversUseCase: GetCurrentDriversUseCase
) : AndroidViewModel(application) {
    val currentDrivers: MutableLiveData<RequestState<DriversResponse>> = MutableLiveData()

    fun getCurrentDrivers() = viewModelScope.launch(Dispatchers.IO) {
        currentDrivers.postValue(RequestState.Loading())
        try {
            if (isNetworkAvailable(application)) {
                val apiResult = getCurrentDriversUseCase.execute()
                currentDrivers.postValue(apiResult)
            }
        } catch (e: Exception) {
            currentDrivers.postValue(RequestState.Error(e.message.toString()))
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
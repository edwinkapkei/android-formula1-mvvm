package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edwinkapkei.formula1.data.model.driver.DriversResponse
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriversUseCase
import com.edwinkapkei.formula1.utilities.NetworkCheck.isNetworkAvailable
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
            }else{
                currentDrivers.postValue(RequestState.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            currentDrivers.postValue(RequestState.Error(e.message.toString()))
        }
    }

}
package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinkapkei.formula1.data.model.driver.DriverAndImage
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriversUseCase
import com.edwinkapkei.formula1.utilities.NetworkCheck.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Year
import javax.inject.Inject

@HiltViewModel
class CurrentDriversViewModel @Inject constructor(
    private val application: Application,
    private val getCurrentDriversUseCase: GetCurrentDriversUseCase
) : ViewModel() {
    val currentDrivers: MutableLiveData<RequestState<List<DriverAndImage>>> = MutableLiveData()

    fun getCurrentDrivers(year: String) = viewModelScope.launch(Dispatchers.IO) {
        currentDrivers.postValue(RequestState.Loading())
        try {
            if (isNetworkAvailable(application)) {
                val apiResult = getCurrentDriversUseCase.execute(year)
                currentDrivers.postValue(apiResult)
            } else {
                currentDrivers.postValue(RequestState.Error(0, "Internet is not available"))
            }
        } catch (e: Exception) {
            currentDrivers.postValue(RequestState.Exception(e))
        }
    }

}
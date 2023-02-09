package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase
import com.edwinkapkei.formula1.utilities.NetworkCheck.isNetworkAvailable
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
}
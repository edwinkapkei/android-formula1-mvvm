package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.utilities.RequestState
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase
import com.edwinkapkei.formula1.utilities.NetworkCheck.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentScheduleViewModel @Inject constructor(
    private val application: Application,
    private val getCurrentScheduleUseCase: GetCurrentScheduleUseCase
) : ViewModel() {
    val currentSchedule: MutableLiveData<RequestState<ScheduleResponse>> = MutableLiveData()

    fun getCurrentSchedule(year: String) = viewModelScope.launch(Dispatchers.IO) {
        currentSchedule.postValue(RequestState.Loading())
        try {
            if (isNetworkAvailable(application)) {
                val apiResult = getCurrentScheduleUseCase.execute(year)
                currentSchedule.postValue(apiResult)
            } else {
                currentSchedule.postValue(RequestState.Error(0, "Internet is not available"))
            }
        } catch (e: Exception) {
            currentSchedule.postValue(RequestState.Exception(e))
        }
    }
}
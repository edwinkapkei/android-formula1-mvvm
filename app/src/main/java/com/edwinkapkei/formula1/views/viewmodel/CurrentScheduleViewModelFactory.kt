package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase

class CurrentScheduleViewModelFactory(
    private val application: Application,
    private val getCurrentScheduleUseCase: GetCurrentScheduleUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrentScheduleViewModel(application, getCurrentScheduleUseCase) as T
    }
}
package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwinkapkei.formula1.domain.usecase.GetCurrentDriversUseCase

class CurrentDriversViewModelFactory(
    private val application: Application,
    private val getCurrentDriversUseCase: GetCurrentDriversUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrentDriversViewModel(application, getCurrentDriversUseCase) as T
    }

}
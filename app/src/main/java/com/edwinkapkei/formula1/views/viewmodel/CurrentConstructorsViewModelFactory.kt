package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edwinkapkei.formula1.domain.usecase.GetCurrentConstructorsUseCase

class CurrentConstructorsViewModelFactory(
    private val application: Application,
    private val getCurrentConstructorsUseCase: GetCurrentConstructorsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrentConstructorsViewModel(application, getCurrentConstructorsUseCase) as T
    }

}
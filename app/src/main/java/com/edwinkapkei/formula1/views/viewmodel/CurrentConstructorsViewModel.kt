package com.edwinkapkei.formula1.views.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.edwinkapkei.formula1.data.model.constructor.ConstructorAndTeamCarImage
import com.edwinkapkei.formula1.domain.usecase.GetCurrentConstructorsUseCase
import com.edwinkapkei.formula1.utilities.NetworkCheck.isNetworkAvailable
import com.edwinkapkei.formula1.utilities.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentConstructorsViewModel
    @Inject
    constructor(
        private val application: Application,
        private val getCurrentConstructorsUseCase: GetCurrentConstructorsUseCase,
    ) : AndroidViewModel(application) {
        val currentConstructors: MutableLiveData<RequestState<List<ConstructorAndTeamCarImage>>> = MutableLiveData()

        fun getCurrentConstructors(year: String) =
            viewModelScope.launch(Dispatchers.IO) {
                currentConstructors.postValue(RequestState.Loading())
                try {
                    if (isNetworkAvailable(application)) {
                        val apiResult = getCurrentConstructorsUseCase.execute(year)
                        currentConstructors.postValue(apiResult)
                    } else {
                        currentConstructors.postValue(RequestState.Error(0, "Internet is not available"))
                    }
                } catch (e: Exception) {
                    currentConstructors.postValue(RequestState.Exception(e))
                }
            }
    }

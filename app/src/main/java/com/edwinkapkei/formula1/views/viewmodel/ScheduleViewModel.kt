package com.edwinkapkei.formula1.views.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwinkapkei.formula1.data.model.schedule.Race
import com.edwinkapkei.formula1.data.model.schedule.ScheduleResponse
import com.edwinkapkei.formula1.domain.usecase.GetCurrentScheduleUseCase
import com.edwinkapkei.formula1.utilities.ErrorProcessing
import com.edwinkapkei.formula1.utilities.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

class ScheduleViewModel(
    private val getCurrentScheduleUseCase: GetCurrentScheduleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    val currentSchedule: MutableLiveData<RequestState<ScheduleResponse>> = MutableLiveData()

    fun getCurrentSchedule(year: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            try {
                when (val apiResult = getCurrentScheduleUseCase.execute(year)) {
                    is RequestState.Success -> {
                        if (apiResult.data?.mRData?.raceTable?.races != null) {
                            _uiState.update { currentState ->
                                currentState.copy(isLoading = false, races = apiResult.data.mRData.raceTable.races)
                            }
                        } else {
                            _uiState.update { currentState ->
                                currentState.copy(isLoading = false, error = "No data found")
                            }
                        }
                    }

                    is RequestState.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                error = ErrorProcessing.getErrorMessage(
                                    apiResult.code,
                                    apiResult.message
                                )
                            )
                        }
                    }

                    is RequestState.Exception -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                error = apiResult.e.message
                            )
                        }
                    }

                    else -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                error = "Something went wrong"
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(isLoading = false, error = e.message)
                }
                currentSchedule.postValue(RequestState.Exception(e))
            }
        }

    fun clearError() {
        _uiState.update { currentState ->
            currentState.copy(error = null)
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val races: List<Race> = emptyList(),
        val error: String? = null
    )
}

val scheduleViewModelModule = module {
    viewModelOf(::ScheduleViewModel)
}

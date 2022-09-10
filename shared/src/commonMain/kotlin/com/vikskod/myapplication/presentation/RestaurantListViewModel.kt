package com.vikskod.myapplication.presentation

import com.vikskod.myapplication.domain.usecase.GetRestaurantUseCase
import com.vikskod.myapplication.domain.util.DataState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val getRestaurantUseCase: GetRestaurantUseCase
) : ViewModel() {

    var state = MutableStateFlow(RestaurantListState())

    fun onIntent(intent: RestaurantListSideEffects) {
        when (intent) {
            is RestaurantListSideEffects.GetRestaurants -> {
                getHeadlines()
            }
        }
    }

    private fun getHeadlines() {

        viewModelScope.launch {

            getRestaurantUseCase.invoke(city = state.value.city, count = state.value.count)
                .collectLatest { dataState ->

                    when (dataState) {
                        is DataState.Success -> {
                            state.emit(
                                state.value.copy(
                                    isLoading = false,
                                    isSuccess = true,
                                    allRestaurantData = dataState.data
                                )
                            )

                        }
                        is DataState.Error -> {

                            state.emit(
                                state.value.copy(
                                    isLoading = false,
                                    isSuccess = false,
                                    error = Error(true, dataState.error.message),
                                )
                            )


                        }
                        else -> {
                            state.emit(
                                state.value.copy(
                                    isLoading = false,
                                    isSuccess = false,
                                    error = Error(true, dataState.error.message),
                                )
                            )

                        }
                    }

                }

        }

    }
}


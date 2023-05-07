package com.mesutemre.namazvakitleri

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val checkVakitInfoExistUseCase: CheckVakitInfoExistUseCase
) : ViewModel() {

    private val _loading = MutableStateFlow(true)
    var loading: StateFlow<Boolean> = _loading

    private val _startDashboard = MutableStateFlow(false)
    var startDashboard: StateFlow<Boolean> = _startDashboard

    init {
        viewModelScope.launch {
            checkVakitInfoExistUseCase().collectLatest {
                _loading.value = false
                _startDashboard.value = it
            }
        }
    }
}
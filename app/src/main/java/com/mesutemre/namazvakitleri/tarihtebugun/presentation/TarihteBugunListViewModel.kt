package com.mesutemre.namazvakitleri.tarihtebugun.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.dashboard.domain.use_case.GetTarihteBugunList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TarihteBugunListViewModel @Inject constructor(
    private val getTarihteBugunList: GetTarihteBugunList
) : ViewModel() {

    private val _state = MutableStateFlow(TarihteBugunListState())
    val state: StateFlow<TarihteBugunListState> = _state

    init {
        viewModelScope.launch {
            getTarihteBugunList().collectLatest { response ->
                _state.update {
                    it.copy(tarihteBugunList = response)
                }
            }
        }
    }
}
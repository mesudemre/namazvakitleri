package com.mesutemre.namazvakitleri.cumamesaj.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.namazvakitleri.cumamesaj.domain.use_case.GetCumaMesajListeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CumaMesajListeViewModel @Inject constructor(
    private val getCumaMesajListeUseCase: GetCumaMesajListeUseCase
) : ViewModel() {

    private val _cumaMesajListeState = MutableStateFlow(CumaMesajListeState())
    val cumaMesajListeState: StateFlow<CumaMesajListeState> = _cumaMesajListeState

    init {
        getCumaMesajList()
    }

    fun getCumaMesajList() {
        viewModelScope.launch {
            getCumaMesajListeUseCase().collectLatest { response ->
                _cumaMesajListeState.update {
                    it.copy(
                        cumaMesajListe = response
                    )
                }
            }
        }
    }
}
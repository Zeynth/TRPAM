package edu.uksw.fti.pam.taspam.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.taspam.repositories.FilmRepository
import kotlinx.coroutines.launch

class NTBViewModel: ViewModel() {
    private var _List = mutableStateListOf<Model>()

    var errorMessage: String by mutableStateOf("")
    val List: List<Model>
        get() = _List

    fun getList() {
        viewModelScope.launch {
            val apiClient = FilmRepository.getClient()
            try {
                _List.clear()
                _List.addAll(apiClient.getNTB())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }
}
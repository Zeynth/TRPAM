package edu.uksw.fti.pam.taspam.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.uksw.fti.pam.taspam.repositories.FilmRepository
import kotlinx.coroutines.launch

class SearchViewModel() : ViewModel() {

    private var _searchList = mutableStateListOf<Model>()

    var errorMessage: String by mutableStateOf("")
    val searchList: List<Model>
        get() = _searchList

    fun getSearchList() {
        viewModelScope.launch {
            val apiClient = FilmRepository.getClient()
            try {
                _searchList.clear()
                _searchList.addAll(apiClient.getNTB())
                _searchList.addAll(apiClient.getJateng())
                _searchList.addAll(apiClient.getSumbar())
            }
            catch (e: Exception) {
                errorMessage = e.message!!
            }
        }
    }

}
package tech.danielwaiguru.androidarchitecturecomponents.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.danielwaiguru.androidarchitecturecomponents.repositories.CharacterRepository
import javax.inject.Inject

class CharacterViewModel
@ViewModelInject constructor(private val characterRepository: CharacterRepository):
    ViewModel() {
    val allCharacters = characterRepository.getAllCharacters()
    fun fetchData() = viewModelScope.launch {
        characterRepository.fetchCharacters()
    }
    fun clearCharacters() = viewModelScope.launch {
        characterRepository.clearCharacters()
    }
}
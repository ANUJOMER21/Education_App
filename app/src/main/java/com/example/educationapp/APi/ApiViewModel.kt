package com.example.educationapp.APi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ApiViewModel(private val repository: RepositoryImpl):ViewModel() {
    private val _loginState = MutableLiveData<LoginUiState>()
    val loginState: LiveData<LoginUiState> = _loginState

    private val _signupState = MutableLiveData<SignupUiState>()
    val signupState: LiveData<SignupUiState> = _signupState
    fun login(username: String, password: String) {
        _loginState.value = LoginUiState.Loading
        viewModelScope. launch {
            val result = repository.login(username, password)
            _loginState.value = result.fold(
                onSuccess = { LoginUiState.Success(it) },
                onFailure = { LoginUiState.Error(it.message ?: "Unknown Error") }
            )
        }
    }

    fun signup(username: String, password: String, phone: String,referal:String) {
        _signupState.value = SignupUiState.Loading
        viewModelScope.launch {
            val result = repository.signup(username, password, phone,referal)
            _signupState.value = result.fold(
                onSuccess = { SignupUiState.Success(it) },
                onFailure = { SignupUiState.Error(it.message ?: "Unknown Error") }
            )
        }
    }
}
sealed class LoginUiState {
    object Loading : LoginUiState()
    data class Success(val response: LoginResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

sealed class SignupUiState {
    object Loading : SignupUiState()
    data class Success(val response: LoginResponse) : SignupUiState()
    data class Error(val message: String) : SignupUiState()
}
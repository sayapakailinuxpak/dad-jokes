package com.eldisprojects.dadjokes.data.remote

sealed class ResponseState<T> {
    data class Loading<T>(val isLoading: Boolean) : ResponseState<T>()
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error<T>(val uiComponent: UIComponent) : ResponseState<T>()
}

sealed class UIComponent() {
    data class Toast(val text: String) : UIComponent()
}
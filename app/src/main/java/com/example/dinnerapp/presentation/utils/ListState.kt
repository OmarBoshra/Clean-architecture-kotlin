package com.example.dinnerapp.presentation.utils

sealed class ListState<T> {
    data class Loading(val message: String) : ListState<Nothing>()

    data class Loaded<T>(val itemState: T) : ListState<T>()

    data class Error(val message: String) : ListState<Nothing>()
}

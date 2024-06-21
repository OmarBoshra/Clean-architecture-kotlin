package com.example.dinnerapp.presentation.utils

sealed class ItemState<T> {
    abstract val id: String?

    data class ItemSelected<T>(val data: T, override val id: String? = null) : ItemState<T>()

    data class ItemNotSelected<T>(val data: T, override val id: String? = null) : ItemState<T>()
}

package com.example.dinnerapp.presentation.epoxy.utils

import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.EpoxyAttribute
import com.example.dinnerapp.presentation.epoxy.events.eventlisteners.MealCategoriesComponentEventListener

abstract class BaseEpoxyModelWithViewHolder<in T : ViewBinding> :
    ViewBindingEpoxyModelWithHolder<T>() {
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var listener: MealCategoriesComponentEventListener? = null
}

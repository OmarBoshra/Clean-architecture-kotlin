package com.example.dinnerapp.presentation.epoxy.components

import android.annotation.SuppressLint
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.dinnerapp.R
import com.example.dinnerapp.databinding.ErrorViewBinding
import com.example.dinnerapp.presentation.epoxy.utils.BaseEpoxyModelWithViewHolder

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.error_view)
abstract class ErrorModel :
    BaseEpoxyModelWithViewHolder<ErrorViewBinding>() {
    @JvmField
    @EpoxyAttribute
    var message: String? = ""

    override fun ErrorViewBinding.bind() {
        errorMessage.text = message
    }
}

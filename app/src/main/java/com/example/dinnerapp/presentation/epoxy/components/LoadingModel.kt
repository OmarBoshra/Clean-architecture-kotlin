package com.example.dinnerapp.presentation.epoxy.components

import android.annotation.SuppressLint
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.example.dinnerapp.R
import com.example.dinnerapp.databinding.LoadingViewBinding
import com.example.dinnerapp.presentation.epoxy.utils.BaseEpoxyModelWithViewHolder

@SuppressLint("NonConstantResourceId")
@EpoxyModelClass(layout = R.layout.loading_view)
abstract class LoadingModel :
    BaseEpoxyModelWithViewHolder<LoadingViewBinding>() {
    @JvmField
    @EpoxyAttribute
    var message: String? = ""

    override fun LoadingViewBinding.bind() {
        tvLoading.text = message
    }
}

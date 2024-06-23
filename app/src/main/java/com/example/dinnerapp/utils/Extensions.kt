package com.example.dinnerapp.utils

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.size.ViewSizeResolver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import coil.request.SuccessResult

inline fun AppCompatActivity.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit,
) {
    lifecycleScope.launch {
        repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}

/**
 * Launches a new coroutine and repeats `block` every time the Fragment's viewLifecycleOwner
 * is in and out of `minActiveState` lifecycle state.
 * Source: https://github.com/google/iosched/blob/main/mobile/src/main/java/com/google/samples/apps/iosched/util/UiUtils.kt#L60
 */
inline fun Fragment.launchAndRepeatWithViewLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    crossinline block: suspend CoroutineScope.() -> Unit,
) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
            block()
        }
    }
}


fun ImageView.loadUrl(
    url: String? = null,
    hideOnLoadFail: Boolean = false,
) {
    if (url == null) {
        if (hideOnLoadFail) {
            this.isVisible = false
        }
        return
    }

    this.load(url) {
        size(ViewSizeResolver(this@loadUrl))
        listener(
            onSuccess = { _, result ->
                try {
                    val bitmap = (result.drawable as BitmapDrawable).bitmap
                    val byteCount = bitmap.allocationByteCount
                    val sizeInKB = byteCount / 1024
                    val sizeInMB = sizeInKB / 1024

                    if (sizeInMB > 10) {
                        throw Exception("Image size is too big - ${sizeInMB}MB - $url")
                    }
                } catch (exception: Exception) {
                    if (hideOnLoadFail) {
                        this@loadUrl.isVisible = false
                    }
                    exception.printStackTrace() // Log the exception message
                }
            },
            onError = { _, _ ->
                if (hideOnLoadFail) {
                    this@loadUrl.isVisible = false
                }
            },
        )
    }
}

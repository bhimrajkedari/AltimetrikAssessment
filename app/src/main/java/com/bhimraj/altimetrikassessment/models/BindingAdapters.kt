package com.bhimraj.altimetrikassessment.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions

import com.bumptech.glide.Glide




object BindingAdapters {
    @BindingAdapter("imageThumb")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl).apply(RequestOptions().circleCrop())
            .into(view)
    }
}
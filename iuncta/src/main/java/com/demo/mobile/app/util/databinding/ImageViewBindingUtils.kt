package com.demo.mobile.app.util.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageViewBindingUtils {

    @JvmStatic
    @BindingAdapter("imageUrl", "placeholder", "viewWidth", "viewHeight", requireAll = false)
    fun ImageView.loadImage(
        url: String,
        placeholder: Drawable?,
        view_width: Int?,
        view_height: Int?
    ) {
        val options = RequestOptions()
        if (view_width != null && view_height != null) options.override(view_width, view_height)
        if (placeholder != null) options.placeholder(placeholder)
        Glide.with(this.context).load(url).apply(options).into(this)
    }

    @JvmStatic
    @BindingAdapter(value = ["simpleResource"])
    fun setStepGroupIcon(imageView: ImageView?, simpleResource: Int) {
        if (simpleResource != -1) {
            imageView?.setImageResource(simpleResource)
        }
    }
}
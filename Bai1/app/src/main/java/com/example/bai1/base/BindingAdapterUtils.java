package com.example.bai1.base;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingAdapterUtils {
    @BindingAdapter("glide_load_url")
    public static void glideLoadImageUrl(ImageView imageView, String url) {
        Glide.with(imageView)
                .load(url)
                .into(imageView);
    }
}

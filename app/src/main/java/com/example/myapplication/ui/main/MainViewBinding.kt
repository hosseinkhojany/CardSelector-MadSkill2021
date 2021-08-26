package com.example.myapplication.ui.main

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.MediaPlayer
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.App
import com.example.myapplication.data.model.response.BaseCard
import com.example.myapplication.data.model.response.Picture
import com.example.myapplication.data.model.response.Sound
import com.example.myapplication.data.model.response.Vibrate
import com.example.myapplication.databinding.PictureItemBinding
import com.example.myapplication.databinding.SoundItemBinding
import com.example.myapplication.databinding.VibratorItemBinding
import com.skydoves.whatif.whatIfNotNullOrEmpty
import timber.log.Timber


object MainViewBinding {

    @JvmStatic
    @BindingAdapter("titleCard")
    fun bindTitle(view: AppCompatTextView, title: String?){
        title.whatIfNotNullOrEmpty {
            view.text = title
        }
    }
    @JvmStatic
    @BindingAdapter("descriptionCard")
    fun bindDescription(view: AppCompatTextView, description: String?){
        description.whatIfNotNullOrEmpty {
            view.text = description
        }
    }

    @JvmStatic
    @BindingAdapter("bindCard")
    fun bindCard(view :FrameLayout, card: BaseCard?){
        if (view.childCount > 0){
            view.removeAllViews()
        }
        card?.run {
            when(card){
                is Picture -> {
                    val binding = PictureItemBinding.inflate(LayoutInflater.from(view.context))
                    view.addView(binding.root)
                    binding.model = card
                }
                is Sound -> {
                    val binding = SoundItemBinding.inflate(LayoutInflater.from(view.context))
                    view.addView(binding.root)
                    binding.model = card
                    try {
                        MediaPlayer().run {
                            val afd: AssetFileDescriptor = App.appContext.assets.openFd("sound.mp3")
                            setDataSource(afd.fileDescriptor,afd.startOffset,afd.length)
                            prepare()
                            start()
                        }
                    }catch (e: Exception){
                        Timber.e(e.message)
                    }
                }
                is Vibrate -> {
                    val binding = VibratorItemBinding.inflate(LayoutInflater.from(view.context))
                    view.addView(binding.root)
                    binding.model = card
                    try {
                        (App.appContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(1000)
                    }catch (e: Exception){
                        Timber.e(e.message)
                    }
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("loadWithGlide")
    fun bindLoadImagePalette(view: AppCompatImageView, url: String) {
        Glide.with(view.context).load(url).into(view)
    }

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, shouldBeGone: Boolean) {
        view.visibility = if (shouldBeGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}

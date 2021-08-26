package com.example.myapplication.ui.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class UbuntuTextView : AppCompatTextView {
    constructor(context: Context) : super(context) {
        this.typeface = Typeface.createFromAsset(context.assets, "ubuntu_font.ttf")
    }
    constructor(context: Context,attributes: AttributeSet) : super(context, attributes) {
        this.typeface = Typeface.createFromAsset(context.assets, "ubuntu_font.ttf")
    }
    constructor(context: Context,attributes: AttributeSet, defStyleAttribute: Int) : super(context, attributes, defStyleAttribute) {
        this.typeface = Typeface.createFromAsset(context.assets, "ubuntu_font.ttf")
    }
}
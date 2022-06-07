package com.okky.ezhousing.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.okky.ezhousing.R

class TambahKeranjangButton : AppCompatButton {
    private var bg: Drawable? = null
    private var txtColor: Int = 0

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = bg

        setTextColor(txtColor)
        textSize = 12f
        gravity = Gravity.CENTER
//        text = "Beli"
    }

    private fun init() {
        txtColor = ContextCompat.getColor(context, R.color.main_color)
        bg = ContextCompat.getDrawable(context, R.drawable.button_bangun_rumah)
    }
}
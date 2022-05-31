package com.okky.ezhousing.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.okky.ezhousing.R

class PasswordEdittext : AppCompatEditText {
    private var bg: Drawable? = null
    private var passwordIcon: Drawable? = null

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
        hint = "Password"
        background = bg
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START

        setCompoundDrawablesWithIntrinsicBounds(passwordIcon, null, null, null)
    }

    private fun init() {
        bg = ContextCompat.getDrawable(context, R.drawable.background_edittext)
        passwordIcon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_lock_24)
    }
}
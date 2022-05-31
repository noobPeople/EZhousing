package com.okky.ezhousing.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.okky.ezhousing.R

class HomeSearchEdittext : AppCompatEditText {
    private var bg: Drawable? = null
    private var searchIcon: Drawable? = null

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
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START

        setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null)
    }

    private fun init() {
        bg = ContextCompat.getDrawable(context, R.drawable.search_home_edittext)
        searchIcon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_search_24)
    }
}
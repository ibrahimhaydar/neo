package com.projects.neo.data.util.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.projects.neo.data.util.AppHelper


class CustomEditText : AppCompatEditText {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()

    }
 private fun init() {
        if (!isInEditMode)
            typeface = AppHelper.getTypeFace(context)
    }

}

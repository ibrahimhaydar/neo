package com.projects.neo.data.util.custom


import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import com.projects.neo.data.util.AppHelper
import me.grantland.widget.AutofitHelper


class CustomTextView : AppCompatTextView {
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
        //resize text if maxline exists as attribute
        AutofitHelper.create(this)
    }

}

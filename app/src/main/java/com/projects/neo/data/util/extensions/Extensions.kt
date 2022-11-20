package com.projects.neo.data.util.extensions


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

import com.projects.neo.R
import java.io.File
import java.util.*


fun CompoundButton.setCustomChecked(
    value: Boolean,
    listener: CompoundButton.OnCheckedChangeListener
) {
    setOnCheckedChangeListener(null)
    isChecked = value
    setOnCheckedChangeListener(listener)
}

fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun String.occurrencesOf(sub: String): Int {
    var count = 0
    var last = 0
    while (last != -1) {
        last = this.indexOf(sub, last)
        if (last != -1) {
            count++
            last += sub.length
        }
    }
    return count
}

inline fun <reified T : Parcelable> Bundle.parcelableArrayList(key: String): ArrayList<T>? = when {
    SDK_INT >= 33 -> getParcelableArrayList(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableArrayList(key)
}

inline fun <reified T : Parcelable> Intent.parcelableArrayList(key: String): ArrayList<T>? = when {
    SDK_INT >= 33 -> getParcelableArrayListExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableArrayListExtra(key)
}

fun AppCompatActivity.addOnBackPressedDispatcher(onBackPressed: () -> Unit = { finish() }) {
    onBackPressedDispatcher.addCallback(
        this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed.invoke()
            }
        }
    )
}

fun ImageView.loadImagesUrl(url: String,loading:LinearLayout) {
    loading.show()
    val options: RequestOptions = RequestOptions()
        .centerCrop()
        .placeholder(R.color.text_gray_light)
        .error(R.color.text_gray_light)
    Glide.with(this)
        .load(url)
        .listener(object : RequestListener<Drawable?> {
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable?>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                try {
                    loading.hide()
                }catch (ex:Exception){}
                return false
            }

            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable?>?,
                isFirstResource: Boolean
            ): Boolean {

                return false

            }

        }).apply(options)
        .into(this)

}


fun ImageView.loadImagePlaceholder(url: String,placeHolder:Int) {

    if(!url.isNullOrEmpty()){
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(placeHolder)
            .error(placeHolder)
        Glide.with(this).load(url).apply(options)
            .into(this)}else{
        this.setImageResource(placeHolder)
    }

}

fun Fragment.loadJSONFromAssets(fileName: String): String {
    return activity!!.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}

fun ImageView.loadImagesUrlResize(url: String) {
    val options: RequestOptions = RequestOptions()
       // .centerCrop()
        .placeholder(R.color.text_gray_light)
        .error(R.color.text_gray_light)
       // .override(560, 150)
        Glide.with(this).load(url).apply(options)
        .into(this)

}

fun TextView.setValueColor(value:Int){
    when {
        value > 0 ->this.setTextColor(ContextCompat.getColor(context,R.color.green))
        value < 0 ->this.setTextColor(ContextCompat.getColor(context,R.color.red))
        else -> this.setTextColor(ContextCompat.getColor(context,R.color.text_gray_light))
    }

}

fun ImageView.loadLocalImage(file: File) {
    val options: RequestOptions = RequestOptions()
        .fitCenter()
        .placeholder(R.color.text_gray_light)
        .error(R.color.text_gray_light)
         Glide.with(this).load(file).apply(options)
        .into(this)

}

fun withDelay(delay : Long, block : () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(block), delay)
}

fun Any.wtf(message: String) {
    debugOnly {
        Log.wtf(this::class.java.simpleName, message)
    }
}

fun Activity.loadJSONFromAssets(fileName: String): String {
    return this.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}


fun View.setWeight(weight:Float){
    val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT
    )
    params.weight = weight
    this.layoutParams = params
}



fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)))
}


fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}



fun Any.logw(key: String, value: String) {

       debugOnly {
           Log.wtf(key, value)
       }

}


inline fun <R> safeCall(call: () -> R): Result<R> {
    try {
        return Result.success(call())
    } catch (e: Exception) {
        return Result.failure(e)
    }
}

fun View.show() {
     safeCall {  visibility = View.VISIBLE }
}

fun View.onSingleClick(action: (v: View) -> Unit) {
    setOnClickListener { v ->
        isClickable = false
        action(v)
        postDelayed({ isClickable = true }, 700)
    }
}

fun View.invisible() {
    try {
        visibility = View.INVISIBLE
    } catch (e: Exception) {
    }
}

fun View.hide() {
    try {
        visibility = View.GONE
    } catch (e: Exception) {
        Log.wtf("visibilityEx",e)
    }
}



fun Activity.createDialog( message: String) {

    val builder = AlertDialog.Builder(this)
    builder
        .setMessage(message)
        .setCancelable(true)
        .setNegativeButton(this.getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
    val alert = builder.create()
    alert.show()
}
fun Fragment.createDialog( message: String) {

    val builder = AlertDialog.Builder(requireActivity())
    builder
        .setMessage(message)
        .setCancelable(true)
        .setNegativeButton(this.getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
    val alert = builder.create()
    alert.show()
}

/**
 * Used for Showing and Hiding keyboard
 */


fun EditText.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun androidx.appcompat.widget.SearchView.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun SearchView.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}


fun String.isValidEmail(): Boolean {
     return (this.trim().isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches())
}

fun Fragment.toast(message: CharSequence){
    safeCall {Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()}
}
fun Activity.toast(message: CharSequence){
    safeCall {Toast.makeText(this, message, Toast.LENGTH_SHORT).show()}
}

fun Context.toast(message: CharSequence){
    safeCall {Toast.makeText(this, message, Toast.LENGTH_SHORT).show()}
}


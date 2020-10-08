package com.gnrchmt.swapi.utils

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.google.gson.LongSerializationPolicy
import es.dmoral.toasty.Toasty
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber

fun logcat(string: String, tag: String? = "SWAPI") {
    Timber.tag(tag).d(string)
}

fun <T> newFragmentInstance(fragmentClass: Class<T>, bundle: Bundle? = null): T {
    val f = Class.forName(fragmentClass.name)
    val a = f.newInstance() as Fragment
    if (bundle != null) {
        a.arguments = bundle
    }

    return a as T
}

fun <T> JSONObject.toObject(useExposed: Boolean, classOfT: Class<T>): T {
    val gson = if (useExposed) GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .setLongSerializationPolicy(LongSerializationPolicy.STRING)
        .create()
    else GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create()
    return gson.fromJson(toString(), classOfT)
}

fun <T> JSONArray.toObjectList(classOfT: Class<T>, useExposed: Boolean = false): List<T> {
    val gson = if (useExposed) GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    else GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).create()
    val list = ArrayList<T>()
    try {
        for (i in 0 until length()) {
            list.add(gson.fromJson(getJSONObject(i).toString(), classOfT))
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return list
}

fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.isVisibleAnimate(boolean: Boolean) {
    if (boolean) {
        alpha = 0f
        isVisible(true)
        animate().alpha(1f).setInterpolator(AccelerateInterpolator()).setDuration(350).start()
    } else {
        animate().alpha(0f).withEndAction { isVisible(false) }.setDuration(350).start()
    }
}

fun Context.toastSuccess(message: String?) {
    Toasty.success(this, "$message").show()
}

fun Context.toastInfo(message: String?) {
    Toasty.info(this, "$message").show()
}

fun Context.toastError(message: String?) {
    Toasty.error(this, "$message").show()
}

fun Fragment.toastSuccess(message: String?) {
    Toasty.success(requireContext(), "$message").show()
}

fun Fragment.toastInfo(message: String?) {
    Toasty.info(requireContext(), "$message").show()
}

fun Fragment.toastError(message: String?) {
    Toasty.error(requireContext(), "$message").show()
}


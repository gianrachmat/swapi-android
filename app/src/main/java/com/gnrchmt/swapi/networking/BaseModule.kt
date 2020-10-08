package com.gnrchmt.swapi.networking

import android.app.Activity
import com.gnrchmt.swapi.utils.ERROR_MESSAGE
import com.gnrchmt.swapi.utils.TAGS
import com.gnrchmt.swapi.utils.logcat
import id.co.nlab.nframework.base.Module
import id.co.nlab.nframework.base.ViewState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

open class BaseModule(private val activity: Activity, private val state: ViewState) {

    companion object {
        const val TAG = "BaseModule"
    }

    fun doRequest(url: String, tag: TAGS = TAGS.TAG) {
        val api = ApiClient().mainClient()
        val module = Module(activity, state)

        module.network.networkConfiguration(tag.name)
        module.network.loading(true, "Loading...")

        GlobalScope.launch {
            try {
                val service = api.getSwapi(url).execute()
                when (service.isSuccessful) {
                    true -> {
                        val body = service.body() ?: "{}"
                        val response = JSONObject(body)
                        logcat(response.toString(), tag.name)
                        if (response.has("detail")) {
                            val detail = response.getString("detail")
                            module.network.failure("", detail)
                        } else {
                            module.network.success(response, "Success")
                        }
                        module.network.loading(false, "")
                    }
                    false -> {
                        module.network.loading(false, "")
                        module.network.failure("", ERROR_MESSAGE)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                module.network.loading(false, "")
                module.network.failure("", ERROR_MESSAGE)
            }
        }
    }

}
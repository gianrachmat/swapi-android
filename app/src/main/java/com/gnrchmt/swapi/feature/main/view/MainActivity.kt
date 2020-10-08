package com.gnrchmt.swapi.feature.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gnrchmt.swapi.R
import com.gnrchmt.swapi.model.ServiceModel
import com.gnrchmt.swapi.networking.BaseModule
import com.gnrchmt.swapi.utils.BASE_URL
import com.gnrchmt.swapi.utils.TAGS
import com.gnrchmt.swapi.utils.ViewPagerAdapter
import com.gnrchmt.swapi.utils.isVisibleAnimate
import com.google.android.material.tabs.TabLayoutMediator
import id.co.nlab.nframework.base.ViewState
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ViewState {

    private val module by lazy { BaseModule(this, this) }
    private val list = arrayListOf<ServiceModel>()
    private val adapter by lazy { ViewPagerAdapter(this, list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    override fun onFailure(data: Any?, tag: String?, message: String?) {
    }

    override fun onLoading(status: Boolean, tag: String?, message: String?) {
        runOnUiThread {
            lay_loading.isVisibleAnimate(status)
            spin_loading.isVisibleAnimate(status)
            tv_loading.text = message ?: ""
        }
    }

    override fun onSuccess(data: Any?, tag: String?, message: String?) {
        when (TAGS.getByName(tag)) {
            TAGS.GET_SERVICE -> {
                if (data is JSONObject) {
                    val services = arrayListOf<ServiceModel>()
                    for (key in data.keys()) {
                        val url = data.getString(key).replace("http", "https")
                        services.add(ServiceModel(key, url))
                    }
                    list.clear()
                    list.addAll(services)

                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                        TabLayoutMediator(tl_main, vp_main) { tab, position ->
                            tab.text = list[position].service.capitalize()
                        }.attach()
                    }
                }
            }
        }
    }

    override fun onUpdate(data: Any?, tag: String?, message: String?) {
    }

    override fun setupView() {
        vp_main.adapter = adapter
        module.doRequest(BASE_URL, TAGS.GET_SERVICE)
    }
}
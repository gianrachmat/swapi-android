package com.gnrchmt.swapi.feature.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnrchmt.swapi.R
import com.gnrchmt.swapi.model.ServiceModel
import com.gnrchmt.swapi.networking.BaseModule
import com.gnrchmt.swapi.utils.*
import id.co.nlab.nframework.base.ViewState
import kotlinx.android.synthetic.main.fragment_list.*
import org.json.JSONObject


class ListFragment : Fragment(), ViewState {

    private val module by lazy { BaseModule(requireActivity(), this) }
    private val adapter by lazy {
        object : Adapter<JSONObject, ListViewHolder>(
            R.layout.item_list,
            ListViewHolder::class.java,
            JSONObject::class.java,
            list
        ) {
            override fun bindView(holder: ListViewHolder, model: JSONObject, position: Int) {
                holder.onBind(model)
            }
        }
    }
    private val service by lazy { arguments?.getParcelable<ServiceModel>("model") }
    private var list = arrayListOf<JSONObject>()
    private var next: String? = null
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logcat("onViewCreated")
        setupView()
    }

    override fun onFailure(data: Any?, tag: String?, message: String?) {
    }

    override fun onLoading(status: Boolean, tag: String?, message: String?) {
        requireActivity().runOnUiThread {
            lay_loading.isVisibleAnimate(status)
            spin_loading.isVisibleAnimate(status)
            tv_loading.text = message ?: ""
            isLoading = status
        }
    }

    override fun onSuccess(data: Any?, tag: String?, message: String?) {
        when (TAGS.getByName(tag)) {
            TAGS.GET_SERVICE, TAGS.GET_UPDATE -> {
                if (data is JSONObject) {
                    requireActivity().runOnUiThread {
                        setupList(data)
                    }
                }
            }
        }
    }

    override fun onUpdate(data: Any?, tag: String?, message: String?) {
    }

    override fun setupView() {
        if (service == null) return
        module.doRequest(service!!.url, TAGS.GET_SERVICE)

        rv_list.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        rv_list.adapter = adapter
        rv_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?

                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == list.size - 1) {
                        loadMore()
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun setupList(json: JSONObject) {
        val data = json.getJSONArray(KEY_RESULTS)
        val next = json.getString(KEY_NEXT)
        val count = json.getString(KEY_COUNT)
        val obj = arrayListOf<JSONObject>()

        for (i in 0 until data.length()) {
            obj.add(data.getJSONObject(i))
        }

        if (next.isNullOrBlank()) {
            toastInfo("Last page")
        }

        this.next = next
        list.addAll(obj)
        adapter.notifyDataSetChanged()
    }

    private fun loadMore() {
        if (next == null) return
        module.doRequest(next!!.replace("http", "https"), TAGS.GET_UPDATE)
    }
}
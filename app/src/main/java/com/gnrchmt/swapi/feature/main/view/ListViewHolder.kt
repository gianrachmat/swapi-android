package com.gnrchmt.swapi.feature.main.view

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import org.json.JSONArray
import org.json.JSONObject

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun onBind(json: JSONObject) {
        itemView.apply {
            var string = ""
            for (key in json.keys()) {
                val text = json.get(key)
                var newText = ""
                if (text is JSONArray) {
                    newText += "<br>"
                    for (i in 0 until text.length()) {
                        val x = text.getString(i)
                        newText += "$x <br>"
                    }
                } else newText = text.toString()
                string += "${key.replace("_", "").capitalize()}: $newText<br>"
            }
            tv_title.movementMethod = LinkMovementMethod.getInstance()
            tv_title.text = Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT)
        }
    }
}
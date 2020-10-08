package com.gnrchmt.swapi.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class Adapter<DataClass, ViewHolder : RecyclerView.ViewHolder>
constructor(
    val mLayout: Int, val mViewHolderClass: Class<ViewHolder>, val mDataClass: Class<DataClass>,
    val mData: List<DataClass>
) : RecyclerView.Adapter<ViewHolder>() {
    private var lastPosition = -1
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(mLayout, parent, false)

        return try {
            var constructor = mViewHolderClass.getConstructor(View::class.java)
            constructor.newInstance(view)
        } catch (error: Exception) {
            throw RuntimeException(error)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)
        bindView(holder, model, position)
        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemView.clearAnimation()
    }

    abstract fun bindView(holder: ViewHolder, model: DataClass, position: Int)
    private fun getItem(position: Int): DataClass {
        return mData[position]
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val layoutManager = recyclerView.layoutManager
            val orientation = when (layoutManager) {
                is LinearLayoutManager -> layoutManager.orientation
                is GridLayoutManager -> layoutManager.orientation
                else -> RecyclerView.VERTICAL
            }
            val animationDirection = if (orientation == RecyclerView.VERTICAL) android.R.anim.fade_in else android.R.anim.slide_in_left
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, animationDirection)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}
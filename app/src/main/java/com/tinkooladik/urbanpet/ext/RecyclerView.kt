package com.tinkooladik.urbanpet.ext

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.initWithAdapter(
    adapter: RecyclerView.Adapter<*>, layoutManager: LinearLayoutManager? = null,
    @DrawableRes dividerRes: Int? = null
) {

    setLayoutManager(layoutManager ?: LinearLayoutManager(context))
    this.adapter = adapter

    if (dividerRes != null) {
        addItemDecoration(getDivider(context, dividerRes, layoutManager))
    }
}

private fun getDivider(
    context: Context, @DrawableRes dividerRes: Int,
    layoutManager: LinearLayoutManager? = null
): DividerItemDecoration {
    val divider = DividerItemDecoration(context, layoutManager?.orientation ?: LinearLayout.VERTICAL)
    divider.setDrawable(ContextCompat.getDrawable(context, dividerRes) ?: BitmapDrawable())
    return divider
}
package com.medhdj.oplay.features.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.medhdj.oplay.R

class GridSpacingItemDecoration(
    context: Context,
    space: Int = R.dimen.spacing_xs
) : ItemDecoration() {
    private val spaceBetweenItems: Int =
        context.resources.getDimension(space).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = spaceBetweenItems;
        outRect.right = spaceBetweenItems;
        outRect.bottom = spaceBetweenItems;

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = spaceBetweenItems;
        } else {
            outRect.top = 0;
        }
    }
}

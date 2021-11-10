package com.medhdj.oplay.features.programs.search

import android.content.Context
import android.util.AttributeSet
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.medhdj.oplay.features.common.GridSpacingItemDecoration
import com.medhdj.oplay.features.programs.ProgramUIModels

class ProgramsGrid @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val listAdapter by lazy { ProgramsGridAdapter() }

    init {
        initList()
    }


    override fun getAdapter(): ProgramsGridAdapter = listAdapter

    suspend fun updateList(data: PagingData<ProgramUIModels.ProgramGridItem>) {
        listAdapter.submitData(data)
    }

    private fun initList() {
        layoutManager = GridLayoutManager(context, COLUMNS_COUNT)
        adapter = listAdapter
        addItemDecoration(GridSpacingItemDecoration(context))
    }
}

private const val COLUMNS_COUNT = 2

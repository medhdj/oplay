package com.medhdj.oplay.features.programs.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.medhdj.oplay.R
import com.medhdj.oplay.databinding.FragmentSearchProgramsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchProgramsFragment : Fragment() {

    private var _binding: FragmentSearchProgramsBinding? = null
    private val binding get() = _binding!!

    private val searchProgramsViewModel by viewModels<SearchProgramsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchProgramsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setupSearchView()
        binding.setupProgramsGrid()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentSearchProgramsBinding.setupSearchView() {
        with(searchView) {
            // hack to open the keyboard
            findViewById<View>(R.id.search_button).performClick()

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                var lastQuery = ""
                override fun onQueryTextSubmit(newQuery: String): Boolean {
                    if (newQuery != lastQuery) {
                        lastQuery = newQuery
                        searchProgramsViewModel.searchPrograms(newQuery)
                        clearFocus()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean = true
            })
        }
    }

    private fun FragmentSearchProgramsBinding.setupProgramsGrid() {
        val gridAdapter = binding.programsGrid.adapter
        gridAdapter.onItemClickListener = {

        }

        gridAdapter.addLoadStateListener { loadState ->
            // Generic error handling here, can be customized to display different UI
            val errorState =
                loadState.source.refresh as? LoadState.Error
                    ?: loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error

            val initialLoadingState = loadState.source.refresh as? LoadState.Loading

            val isListEmpty =
                errorState == null &&
                        loadState.refresh is LoadState.NotLoading &&
                        loadState.prepend.endOfPaginationReached &&
                        loadState.append.endOfPaginationReached &&
                        gridAdapter.itemCount == 0

            val isRefreshSuccessful =
                loadState.source.refresh is LoadState.NotLoading || loadState.mediator?.refresh is LoadState.NotLoading

            errorView.isVisible = errorState != null
            progressBar.isVisible = initialLoadingState != null
            emptyView.isVisible = isListEmpty
            programsGrid.isVisible = isRefreshSuccessful
        }

        searchProgramsViewModel.pagingData.observe(viewLifecycleOwner) { pagingDataFlow ->
            lifecycleScope.launch {
                pagingDataFlow.collectLatest(gridAdapter::submitData)
            }
        }
    }
}

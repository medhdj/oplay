package com.medhdj.oplay.features.programs.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
        setupProgramsGrid()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupProgramsGrid() {
        val gridAdapter = binding.programsGrid.adapter
        gridAdapter.onItemClickListener = {

        }

        searchProgramsViewModel.pagingData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                it.collectLatest(gridAdapter::submitData)
            }
        }
    }
}

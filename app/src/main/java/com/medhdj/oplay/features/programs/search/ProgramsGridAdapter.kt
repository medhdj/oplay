package com.medhdj.oplay.features.programs.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.medhdj.core.extension.setImage
import com.medhdj.oplay.R
import com.medhdj.oplay.databinding.ProgramGridItemBinding
import com.medhdj.oplay.features.programs.ProgramUIModels


class ProgramsGridAdapter :
    PagingDataAdapter<ProgramUIModels.Program, ProgramsGridAdapter.ProgramGridItemViewHolder>(
        COMPARATOR
    ) {

    var onItemClickListener: ((ProgramUIModels.Program) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramGridItemViewHolder =
        ProgramGridItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: ProgramGridItemViewHolder, position: Int) {
        val program = getItem(position)
        program?.let {
            holder.bind(it, onItemClickListener)
        }
    }

    // View Holders
    class ProgramGridItemViewHolder(private val programGridItemBinding: ProgramGridItemBinding) :
        RecyclerView.ViewHolder(programGridItemBinding.root) {
        fun bind(
            program: ProgramUIModels.Program,
            onItemClickListener: ((ProgramUIModels.Program) -> Unit)?
        ) = with(programGridItemBinding) {
            programImage.setImage(program.imageUrl, R.drawable.ic_launcher_foreground)

            programInfo.text = "${program.title}\n${program.subtitle}"

            itemView.setOnClickListener {
                onItemClickListener?.invoke(program)
            }
        }

        companion object {
            fun create(parent: ViewGroup): ProgramGridItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val programGridItemBinding = ProgramGridItemBinding.inflate(inflater, parent, false)
                return ProgramGridItemViewHolder(programGridItemBinding)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ProgramUIModels.Program>() {
            override fun areItemsTheSame(
                oldItem: ProgramUIModels.Program, newItem: ProgramUIModels.Program
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ProgramUIModels.Program, newItem: ProgramUIModels.Program
            ): Boolean = oldItem == newItem
        }
    }
}

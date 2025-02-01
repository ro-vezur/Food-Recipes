package com.example.foodrecipes.presentation.detailedRecipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.foodrecipes.databinding.ItemInstructionBinding

class InstructionsAdapter(private val instructions: List<String>): RecyclerView.Adapter<InstructionsAdapter.InstructionsViewHolder>() {

    inner class InstructionsViewHolder(private val binding: ItemInstructionBinding): ViewHolder(binding.root) {
        fun bind(instruction: String,position: Int) {
            with(binding) {
                tvStepNumber.text = "${position + 1}"
                tvInstruction.text = instruction
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        val binding = ItemInstructionBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )

        return InstructionsViewHolder(binding)
    }

    override fun getItemCount(): Int = instructions.size

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        val instruction = instructions[position]

        holder.bind(instruction, position)
    }
}
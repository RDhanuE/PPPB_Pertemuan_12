package com.example.tugaspertemuan12

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaspertemuan12.databinding.ItemCharactersBinding

typealias OnClickUpdate = (Characters) -> Unit
typealias OnClickDelete = (Characters) -> Unit

class CharactersAdapter (private val listCharacters: List<Characters>, private val onClickUpdate: OnClickUpdate, private val onClickDelete: OnClickDelete) : RecyclerView.Adapter<CharactersAdapter.ItemCharactersViewHolder>() {

    inner class ItemCharactersViewHolder (private val binding: ItemCharactersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Characters) {
            with(binding){
                charName.text = data.char_name
                charClass.text = data.char_class
                charHP.text = data.hp.toString()
                charMana.text = data.mana.toString()
                charWeapon.text = data.char_weapon
                charUpdateButton.setOnClickListener{
                    onClickUpdate(data)
                }
                charDeleteButton.setOnClickListener{
                    onClickDelete(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCharactersViewHolder {
        val binding = ItemCharactersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemCharactersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    override fun onBindViewHolder(holder: ItemCharactersViewHolder, position: Int) {
        holder.bind(listCharacters[position])
    }
}
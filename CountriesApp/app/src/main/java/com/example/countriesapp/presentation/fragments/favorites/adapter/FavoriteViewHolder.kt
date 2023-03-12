package com.example.countriesapp.presentation.fragments.favorites.adapter

import android.graphics.BitmapFactory
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ItemSavedPlaceBinding
import com.example.countriesapp.framework.local.room.CountryEntity

class FavoriteViewHolder(view: View):ViewHolder(view), View.OnClickListener {

    private val binding = ItemSavedPlaceBinding.bind(view)
    private lateinit var localEntity:CountryEntity
    private lateinit var onLocalItemSelected: (CountryEntity,Int,Int) -> Unit
    init{
        binding.ibThreeDots.setOnClickListener(this)
    }

    fun render(entity: CountryEntity, onItemClicked: (CountryEntity,Int,Int) -> Unit){
        localEntity = entity
        onLocalItemSelected = onItemClicked
        with(binding){
            if(entity.photoPath != ""){
                val bitmap = BitmapFactory.decodeFile(entity.photoPath)
                ivSavedPlace.setImageBitmap(bitmap)
            }
            tvDate.text = entity.date
            tvLocation.text = entity.address
        }
    }

    override fun onClick(v: View?) {
        if (v ==  binding.ibThreeDots){
            val popupMenu = PopupMenu(v.context,v)
            popupMenu.inflate(R.menu.popup_menu_item)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.deleteItem -> {
                        val position = adapterPosition
                        onLocalItemSelected(localEntity,0,position)
                        true
                    }
                    R.id.label_description ->{
                        Toast.makeText(v.context,"DESCRIPTION",Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }
    }


}
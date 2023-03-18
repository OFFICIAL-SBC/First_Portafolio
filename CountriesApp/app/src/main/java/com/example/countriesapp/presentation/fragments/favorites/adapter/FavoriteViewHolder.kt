package com.example.countriesapp.presentation.fragments.favorites.adapter

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.*
import com.example.countriesapp.R
import com.example.countriesapp.databinding.ItemSavedPlaceBinding
import com.example.countriesapp.framework.local.room.CountryEntity

class FavoriteViewHolder(view: View):ViewHolder(view), View.OnClickListener {

    private val binding = ItemSavedPlaceBinding.bind(view)
    private lateinit var localEntity:CountryEntity
    private lateinit var onLocalItemSelected: (Int,String,Int,Int) -> Unit

    private var flag: Boolean = false

    init{
        binding.ibThreeDots.setOnClickListener(this)
        binding.ibShowMore.setOnClickListener(this)
    }

    fun render(entity: CountryEntity, onItemClicked: (Int,String,Int,Int) -> Unit){
        localEntity = entity
        onLocalItemSelected = onItemClicked
        with(binding){
            if(entity.photoPath != ""){
                val bitmap = BitmapFactory.decodeFile(entity.photoPath)
                ivSavedPlace.setImageBitmap(bitmap)
            }
            binding.tvDescription.text = if (localEntity.description.isEmpty()) "There is not description availale"
            else localEntity.description
            tvDate.text = entity.date
            tvLocation.text = entity.address
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        if (v ==  binding.ibThreeDots){
            val popupMenu = PopupMenu(v.context,v)
            popupMenu.inflate(R.menu.popup_menu_item)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener { item ->
                val position = adapterPosition
                when(item.itemId){
                    R.id.deleteItem -> {
                        onLocalItemSelected(localEntity.id,localEntity.description,0,position)
                        true
                    }
                    R.id.label_description ->{
                        onLocalItemSelected(localEntity.id,localEntity.description,1,position)
                        true
                    }
                    else -> false
                }
            }
        }else if(v == binding.ibShowMore){
            with(binding.tvDescription){
                if(!flag){
                    visibility = VISIBLE
                    binding.ibShowMore.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    flag = !flag
                }else{
                    visibility= GONE
                    binding.ibShowMore.setImageResource(R.drawable.heyboard_down_narrow_icon)
                    flag = !flag
                }
            }


        }
    }


}
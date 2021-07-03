package com.example.navigationdrawer.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.ItemsFavouriteBinding
import com.squareup.picasso.Picasso

class AdapterFavourites(private val items: List<Meals>,private val itemClickListener: OnMealsListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var itemsFavouriteBinding: ItemsFavouriteBinding

    interface OnMealsListener{
        fun deleteFavouriteListener(item:Meals,position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        itemsFavouriteBinding= ItemsFavouriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemsFavouriteBinding)

    }
    override fun getItemCount():Int{
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        itemsFavouriteBinding.txtTitleFavourite.text=items[position].title
        itemsFavouriteBinding.imgVMealFavourite.loadUrl(items[position].image)
        itemsFavouriteBinding.txtDescriptionFavourite.text=items[position].description

        itemsFavouriteBinding.btnDeleteFavourite.setOnClickListener {
            itemClickListener.deleteFavouriteListener(items[position],position)
            val lista = items as ArrayList
            lista.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, itemCount)
        }
        itemsFavouriteBinding.cardViewFavourite.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(AdapterMeals.MEALS_ITEMS,items[position])
            it.findNavController().navigate(R.id.action_nav_gallery_to_mealDetailFragment,bundle)
        }


    }
    private fun ImageView.loadUrl(url: String){
        Picasso.get().load(url).into(itemsFavouriteBinding.imgVMealFavourite)
    }




    class ViewHolder(binding: ItemsFavouriteBinding):RecyclerView.ViewHolder(binding.root)



}
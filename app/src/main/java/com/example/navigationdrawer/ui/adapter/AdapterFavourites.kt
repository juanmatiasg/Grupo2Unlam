package com.example.navigationdrawer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.ItemsFavouriteBinding
import com.example.navigationdrawer.databinding.ItemsMealBinding
import com.squareup.picasso.Picasso
import java.text.FieldPosition

class AdapterFavourites(private val items: ArrayList<Meals>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var itemsFavouriteBinding: ItemsFavouriteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        itemsFavouriteBinding= ItemsFavouriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemsFavouriteBinding)

    }
    override fun getItemCount():Int{
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        itemsFavouriteBinding.txtTitleFavourite.text=items[position].title
        itemsFavouriteBinding.txtDescriptionFavourite.text=items[position].protein
        itemsFavouriteBinding.imgVMealFavourite.loadUrl(items[position].image)
    }
    private fun ImageView.loadUrl(url: String){
        Picasso.get().load(url).into(itemsFavouriteBinding.imgVMealFavourite)
    }

    fun getAddListMeals(list: ArrayList<Meals>){
        items.clear()
        items.addAll(list)

    }

    class ViewHolder(binding: ItemsFavouriteBinding):RecyclerView.ViewHolder(binding.root)

    companion object{
        const val FAVOURITE_ITEMS ="FAVOURITE_ITEMS"
    }

}
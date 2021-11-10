package com.example.navigationdrawer.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.ItemCarouselBinding
import com.squareup.picasso.Picasso

class AdapterLunch(private val items: List<Meals>, private val itemClickListener: AdapterLunch.OnMealsListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnMealsListener{
        fun deleteFavouriteListener(item:Meals,position: Int)
    }

    private lateinit var itemsLunchBinding: ItemCarouselBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        itemsLunchBinding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdapterHome.ViewHolder(itemsLunchBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemsLunchBinding.txtTitleMain.text = items[position].title
        itemsLunchBinding.imgVMealMain.loadUrl(items[position].image)
        itemsLunchBinding.btnUiClearMain.setOnClickListener {
            itemClickListener.deleteFavouriteListener(items[position],position)
            val lista = items as ArrayList
            lista.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position,itemCount)
        }
        itemsLunchBinding.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(AdapterLunch.MEALS_ITEMS,items[position])
            it.findNavController().navigate(R.id.action_plannerFragment_to_mealDetailFragment)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun ImageView.loadUrl(url:String){
        Picasso.get().load(url).into(itemsLunchBinding.imgVMealMain)
    }

    companion object{
        const val MEALS_ITEMS ="MEALS_ITEMS"
    }

}
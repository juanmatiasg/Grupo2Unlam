package com.example.navigationdrawer.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.ItemCarouselBinding
import com.squareup.picasso.Picasso

class AdapterDinner(private val items: List<Meals>, private val itemClickListener: AdapterDinner.OnMealsListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnMealsListener{
        fun deleteFavouriteListener(item:Meals,position: Int)
    }


    private lateinit var itemsDinnerBinding: ItemCarouselBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        itemsDinnerBinding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdapterHome.ViewHolder(itemsDinnerBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemsDinnerBinding.txtTitleMain.text = items[position].title
        itemsDinnerBinding.imgVMealMain.loadUrl(items[position].image)
        itemsDinnerBinding.btnUiClearMain.setOnClickListener {
            itemClickListener.deleteFavouriteListener(items[position],position)
            val lista = items as ArrayList
            lista.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position,itemCount)
        }
        itemsDinnerBinding.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(AdapterDinner.MEALS_ITEMS,items[position])
            it.findNavController().navigate(R.id.action_plannerFragment_to_mealDetailFragment)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun ImageView.loadUrl(url:String){
        Picasso.get().load(url).into(itemsDinnerBinding.imgVMealMain)
    }

    companion object{
        const val MEALS_ITEMS ="MEALS_ITEMS"
    }
}
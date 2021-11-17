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

class AdapterBreakfast (private val items: List<Meals>, private val itemClickListener: AdapterBreakfast.OnMealsListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnMealsListener{
        fun deleteBreakfastListener(item:Meals, position: Int)
    }

    private lateinit var itemsBreakfastBinding: ItemCarouselBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        itemsBreakfastBinding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdapterHome.ViewHolder(itemsBreakfastBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemsBreakfastBinding.txtTitleMain.text = items[position].title
        itemsBreakfastBinding.imgVMealMain.loadUrl(items[position].image)
        itemsBreakfastBinding.btnUiClearMain.setOnClickListener {
            itemClickListener.deleteBreakfastListener(items[position],position)
            val lista = items as ArrayList
            lista.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position,itemCount)
        }
        itemsBreakfastBinding.cardView.setOnClickListener {
            val bundle= Bundle()
            bundle.putParcelable(AdapterMeals.MEALS_ITEMS,items[position])
            it.findNavController().navigate(R.id.action_plannerFragment_to_mealDetailFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    private fun ImageView.loadUrl(url:String){
        Picasso.get().load(url).into(itemsBreakfastBinding.imgVMealMain)
    }

    companion object{
        const val MEALS_ITEMS ="MEALS_ITEMS"
    }
}
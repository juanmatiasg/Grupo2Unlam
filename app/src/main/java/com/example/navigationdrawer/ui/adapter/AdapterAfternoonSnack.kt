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

class AdapterAfternoonSnack(private val items: List<Meals>, private val itemClickListener: AdapterAfternoonSnack.OnMealsListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnMealsListener{
        fun deleteAfternoonSnackListener(item:Meals, position: Int)
    }

    private lateinit var itemsAfternoonSnackBinding: ItemCarouselBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        itemsAfternoonSnackBinding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdapterHome.ViewHolder(itemsAfternoonSnackBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemsAfternoonSnackBinding.txtTitleMain.text = items[position].title
        itemsAfternoonSnackBinding.imgVMealMain.loadUrl(items[position].image)
        itemsAfternoonSnackBinding.btnUiClearMain.setOnClickListener {
            itemClickListener.deleteAfternoonSnackListener(items[position],position)
            val lista = items as ArrayList
            lista.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position,itemCount)
        }
        itemsAfternoonSnackBinding.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(AdapterMeals.MEALS_ITEMS,items[position])
            it.findNavController().navigate(R.id.action_plannerFragment_to_mealDetailFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun ImageView.loadUrl(url:String){
        Picasso.get().load(url).into(itemsAfternoonSnackBinding.imgVMealMain)
    }

    companion object{
        const val MEALS_ITEMS ="MEALS_ITEMS"
    }
}
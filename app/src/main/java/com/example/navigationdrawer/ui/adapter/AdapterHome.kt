package com.example.navigationdrawer.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.entities.MealEntity
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.databinding.ItemsMainBinding
import com.example.navigationdrawer.databinding.ItemsMealBinding
import com.squareup.picasso.Picasso

class AdapterHome(private val items: List<Meals>, private val itemClickListener: OnMealsListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnMealsListener{
        fun deleteFavouriteListener(item:Meals,position: Int)
    }

    private lateinit var itemsMainBinding: ItemsMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        itemsMainBinding= ItemsMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemsMainBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemsMainBinding.txtTitleMain.text=items[position].title
        itemsMainBinding.imgVMealMain.loadUrl(items[position].image)
        itemsMainBinding.textViewDescriptionMain.text = items[position].description

        itemsMainBinding.btnUiClearMain.setOnClickListener{
            itemClickListener.deleteFavouriteListener(items[position],position)
            val lista= items as ArrayList
            lista.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeRemoved(position, itemCount)
        }
        itemsMainBinding.cardViewHome.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(AdapterMeals.MEALS_ITEMS,items[position])
            it.findNavController().navigate(R.id.action_nav_home_to_mealDetailFragment3,bundle)

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
    private fun ImageView.loadUrl(url:String){
        Picasso.get().load(url).into(itemsMainBinding.imgVMealMain)
    }



    /*fun getAddListMeals(list: ArrayList<Meals>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }*/
    class ViewHolder(binding: ItemsMainBinding):RecyclerView.ViewHolder(binding.root)
}

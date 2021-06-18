package com.example.navigationdrawer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.*
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.FragmentHomeBinding
import com.example.navigationdrawer.databinding.ItemsMainBinding
import com.example.navigationdrawer.databinding.ItemsMealBinding
import com.squareup.picasso.Picasso

class AdapterHome(private val items: ArrayList<Meals>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var itemsMainBinding: ItemsMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        itemsMainBinding= ItemsMainBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemsMainBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemsMainBinding.txtTitleMain.text=items[position].title
        itemsMainBinding.imgVMealMain.loadUrl(items[position].image)
        itemsMainBinding.txtDescriptionMain.text=items[position].protein
    }

    override fun getItemCount(): Int {
        return items.size
    }
    private fun ImageView.loadUrl(url:String){
        Picasso.get().load(url).into(itemsMainBinding.imgVMealMain)
    }
    fun getAddListMeals(list: ArrayList<Meals>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
    class ViewHolder(binding: ItemsMainBinding):RecyclerView.ViewHolder(binding.root)
}

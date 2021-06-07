package com.example.navigationdrawer.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.ItemsMealBinding
import com.squareup.picasso.Picasso

class AdapterMeals (private val items: ArrayList<Meals>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private lateinit var itemsMealBinding: ItemsMealBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        itemsMealBinding = ItemsMealBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemsMealBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemsMealBinding.textViewTitle.text = items[position].title
        itemsMealBinding.imageView.loadUrl(items[position].image)
    }

    private fun ImageView.loadUrl(url:String){
        Picasso.get().load(url).into(itemsMealBinding.imageView)
    }

    fun getAddListMeals(list: ArrayList<Meals>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(binding:ItemsMealBinding):RecyclerView.ViewHolder(binding.root)



}
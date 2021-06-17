package com.example.navigationdrawer.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationdrawer.R
import com.example.navigationdrawer.data.model.Meals
import com.example.navigationdrawer.databinding.ItemsMealBinding
import com.squareup.picasso.Picasso

class AdapterMeals (private val items: List<Meals>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
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
        itemsMealBinding.cardView.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelable(MEALS_ITEMS,items[position])
            it.findNavController().navigate(R.id.action_nav_mealFragment_to_mealDetailFragment,bundle)
        }
    }

    private fun ImageView.loadUrl(url:String){
        Picasso.get().load(url).into(itemsMealBinding.imageView)
    }



    class ViewHolder(binding:ItemsMealBinding):RecyclerView.ViewHolder(binding.root)

    companion object{
        const val MEALS_ITEMS ="MEALS_ITEMS"
    }


}
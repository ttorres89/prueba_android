package com.example.criptomonedas_tania.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.criptomonedas_tania.R
import com.example.criptomonedas_tania.data.model.Criptomoneda
import com.squareup.picasso.Picasso

class CustomAdapter (private val critoList:List<Criptomoneda>, private val context: Context): RecyclerView.Adapter<CustomAdapter.ViewHolder>(), View.OnClickListener {

    private lateinit var  listener: View.OnClickListener

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)


        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        v.setOnClickListener(this)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = critoList[i].name
        viewHolder.itemDetail.text = critoList[i].symbol
        Picasso.with(context).load(critoList[i].logo_url).fit().centerInside().noFade().into(viewHolder.itemImage)
        //viewHolder.itemImage.load(this.critoList.get(i).logo_url)




    }

    override fun getItemCount(): Int {
        return critoList.size
    }

    fun setOnClickListener(listener: View.OnClickListener){
        this.listener = listener
    }

    override fun onClick(v: View?) {
        if (listener!=null){
            listener.onClick(v)
        }
    }
}
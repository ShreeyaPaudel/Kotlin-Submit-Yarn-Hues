package com.example.a35b_crud.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a35b_crud.R
import com.example.a35b_crud.model.ProductModel
import com.example.a35b_crud.ui.activity.UpdateProductActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.util.ArrayList

class ProductAdapter(val context: Context,
                     var data : ArrayList<ProductModel>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){

        val imageView : ImageView = itemView.findViewById(R.id.getImage)
        val loading : ProgressBar = itemView.findViewById(R.id.progressBar2)
        val editButton : TextView = itemView.findViewById(R.id.lblEdit)
        val pName : TextView = itemView.findViewById(R.id.displayName)
        val pPrice : TextView = itemView.findViewById(R.id.displayPrice)
        val pDesc : TextView = itemView.findViewById(R.id.displayDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView : View = LayoutInflater.from(context).
        inflate(R.layout.sample_products,parent,false)

        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.pName.text = data[position].productName
        holder.pPrice.text = data[position].price.toString()
        holder.pDesc.text = data[position].productDesc

        Picasso.get().load(data[position].imageUrl).into(holder.imageView,object:Callback{
            override fun onSuccess() {
                holder.loading.visibility = View.GONE
            }

            override fun onError(e: Exception?) {

            }

        })


        holder.editButton.setOnClickListener {
            val intent = Intent(context,UpdateProductActivity::class.java)
//            if model pass garnu paryo bhane
//            first make model parcelable
//            intent.putExtra("products",data[position])

            intent.putExtra("productId",data[position].productId)

            context.startActivity(intent)
        }
    }

    fun updateData(products: List<ProductModel>){
        data.clear()
        data.addAll(products)
        notifyDataSetChanged()
    }

    fun getProductId(position: Int) : String{
        return data[position].productId
    }

}
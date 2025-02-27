package com.example.a35b_crud.ui.activity

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a35b_crud.R
import com.example.a35b_crud.databinding.ActivityOrderBinding


class OrderActivity : AppCompatActivity() {

    lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve order data from SharedPreferences
        val sharedPreferences = getSharedPreferences("Orders", Context.MODE_PRIVATE)
        val itemName = sharedPreferences.getString("order_name", "No Orders")
        val itemPrice = sharedPreferences.getString("order_price", "0")
        val itemImage = sharedPreferences.getInt("order_image", 0)


        val address = sharedPreferences.getString("order_address", "No Address")
        val date = sharedPreferences.getString("order_date", "No Date")





        // Display the order details
        binding.tvItemName.text = itemName
        binding.tvItemPrice.text = "Price: $itemPrice"
        binding.ivItemImage.setImageResource(itemImage)
        binding.tvAddress.text = "Delivery Address: $address"
        binding.tvDeliveryDate.text = "Delivery Date: $date"



    }
}
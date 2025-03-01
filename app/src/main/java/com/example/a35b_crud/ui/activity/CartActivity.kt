package com.example.a35b_crud.ui.activity

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a35b_crud.R
import com.example.a35b_crud.databinding.ActivityCartBinding
import com.example.a35b_crud.ui.fragment.HomeFragment
import com.example.a35b_crud.ui.fragment.ProfileFragment

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemName = intent.getStringExtra("itemName") ?: "No Item"
        val itemPrice = intent.getStringExtra("itemPrice")?: "0"
        val itemImage = intent.getIntExtra("itemImage", 0) // Assuming it's a drawable resource

        binding.itemName.text = itemName
        binding.itemPrice.text = "Price: $itemPrice"
        binding.itemImage.setImageResource(itemImage) // Assuming image is passed as a drawable resource ID


        binding.deliveryMethod.text = "Cash on Delivery"

        binding.selectDeliveryDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                binding.deliveryDate.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            }, year, month, day)

            datePicker.show()
        }

        binding.orderButton.setOnClickListener {
            val address = binding.addressInput.text.toString()
            val deliveryDate = binding.deliveryDate.text.toString()

            if (address.isEmpty() || deliveryDate.isEmpty()) {
                Toast.makeText(this, "Please enter address and select a delivery date", Toast.LENGTH_SHORT).show()
            } else {
                saveOrderToPreferences(itemName, itemPrice, itemImage, address, deliveryDate)
                Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show()

                // Navigate to Profile (or any other relevant page)
                startActivity(Intent(this, OrderActivity::class.java))
                finish()
            }



        }
    }

    private fun saveOrderToPreferences(name: String, price: String, image: Int, address: String, date: String) {
        val sharedPreferences = getSharedPreferences("Orders", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("order_name", name)
        editor.putString("order_price", price)
        editor.putInt("order_image", image)
        editor.putString("order_address", address)
        editor.putString("order_date", date)
        editor.apply()
    }
}
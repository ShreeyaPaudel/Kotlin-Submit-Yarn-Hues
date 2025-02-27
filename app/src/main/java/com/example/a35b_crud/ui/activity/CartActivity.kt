package com.example.a35b_crud.ui.activity

import android.app.DatePickerDialog
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

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemName = intent.getStringExtra("itemName")
        val itemPrice = intent.getStringExtra("itemPrice")
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

            if (address.isEmpty()) {
                Toast.makeText(this, "Please enter your address", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show()

                // Navigate back to Home or Order Summary
                val intent = Intent(this, HomeFragment::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }


        }
    }
}
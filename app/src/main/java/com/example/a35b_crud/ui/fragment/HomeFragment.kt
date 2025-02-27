package com.example.a35b_crud.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a35b_crud.R
import com.example.a35b_crud.databinding.FragmentHomeBinding
import com.example.a35b_crud.ui.activity.CartActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Example for multiple buttons with different product details

        binding.btnBuyNowMuffler.setOnClickListener {
            navigateToCart("Handmade Yarn Scarf for this winter.", "Rs.700", R.drawable.muffler)
        }

        binding.btnBuySocks.setOnClickListener {
            navigateToCart("Woolen Socks to keep your feet delicate.", "Rs.350", R.drawable.socks)
        }

        binding.btnBuyNowHat.setOnClickListener {
            navigateToCart("Knitted Hat to rescue you from the wind", "$15", R.drawable.hat)
        }

        binding.btnBuyNowPurple.setOnClickListener {
            navigateToCart("Knitted/Crochet Earwarmer for warmth amidst the winter", "Rs. 850", R.drawable.earwarmerpurple)
        }
        binding.btnBuyNowRedWhiteHat.setOnClickListener {
            navigateToCart("Crocheted Extra soft hat ", "Rs.1500", R.drawable.redwhitehat)
        }
    }

    // Function to navigate to CartActivity with product details
    private fun navigateToCart(itemName: String, itemPrice: String, itemImage: Int) {
        val intent = Intent(requireContext(), CartActivity::class.java)
        intent.putExtra("itemName", itemName)
        intent.putExtra("itemPrice", itemPrice)
        intent.putExtra("itemImage", itemImage)
        startActivity(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Prevent memory leaks
    }
}

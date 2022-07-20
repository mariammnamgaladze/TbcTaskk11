package com.example.tbctaskk11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbctaskk11.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickListeners()
    }


    private fun onClickListeners() {
        binding.addBtn2.setOnClickListener {
            val description = binding.addEditText.toString()
            val attributes = Attributes(null, description)
            attributesList.add(attributes)
            finish()
        }
    }
}
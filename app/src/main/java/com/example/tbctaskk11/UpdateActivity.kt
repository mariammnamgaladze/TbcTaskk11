package com.example.tbctaskk11

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbctaskk11.MainActivity.Companion.ATT_ID
import com.example.tbctaskk11.MainActivity.Companion.POSITION
import com.example.tbctaskk11.databinding.ActivityUpdateBinding

class UpdateActivity: AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding

    private lateinit var attributes :Attributes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        attributes = intent.getParcelableExtra<Attributes>(ATT_ID)!!

        binding.updateEditText.setText(attributes.description)


        onClickListeners()
    }


    private fun onClickListeners() {
        binding.updateBtn2.setOnClickListener {
            attributes.description = binding.updateEditText.text.toString()
            val rusultIntent = Intent()
            rusultIntent.putExtra(ATT_ID,attributes)
            rusultIntent.putExtra(POSITION,intent.getIntExtra(POSITION,-1))

            setResult(Activity.RESULT_OK,rusultIntent)
            onBackPressed()
        }
    }
}
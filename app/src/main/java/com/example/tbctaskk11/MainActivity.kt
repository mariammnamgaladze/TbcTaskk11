package com.example.tbctaskk11

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tbctaskk11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private val recyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        swipeRefresh = binding.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                //Do something after 1500ms
                recyclerAdapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }, 1500)
        }

        init()
        onClickListeners()
    }

    override fun onResume() {
        super.onResume()
        recyclerAdapter.setData(attributesList.toList())
    }

    private fun init() {

        binding.recyclerView.apply {

            adapter = recyclerAdapter
            layoutManager = GridLayoutManager(applicationContext, 2)
        }
        // recyclerAdapter.setData(attributesList.toList())
    }

    private fun onClickListeners() {

        binding.addBtn1.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddActivity::class.java))
        }

        recyclerAdapter.onEditClickListener = { att: Attributes, pos: Int ->
            startActivityForResult(Intent(this, UpdateActivity::class.java).apply {
                putExtra(ATT_ID, att)
                putExtra(POSITION, pos)
            }, EDIT_CODE)

        }

        recyclerAdapter.onDeleteClickListener = {
            attributesList.remove(it)
            recyclerAdapter.setData(attributesList.toList())
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                EDIT_CODE -> {
                    val position = data!!.getIntExtra(POSITION, -1)
                    if (position != -1) {
                        val attr = data!!.getParcelableExtra<Attributes>(ATT_ID)
                        attributesList.set(position, attr!!)
                        recyclerAdapter.notifyItemChanged(position)
                    }
                }
            }
        }
    }

    companion object {
        const val EDIT_CODE = 55
        const val ATT_ID = "attributes"
        const val POSITION = "position"

    }
}
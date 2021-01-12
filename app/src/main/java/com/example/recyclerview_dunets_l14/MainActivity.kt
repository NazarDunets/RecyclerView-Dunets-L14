package com.example.recyclerview_dunets_l14

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_dunets_l14.databinding.ActivityMainBinding
import com.example.recyclerview_dunets_l14.family_tree.PeopleGenerator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val me = PeopleGenerator().getMe()
        val density = this.resources.displayMetrics.density
        binding.rvMain.run {
            adapter = FamilyAdapter(me.listLeveledTree(), density)
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
            )
        }

    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
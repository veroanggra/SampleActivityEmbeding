package com.veroanggra.sampleactivityembeding

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.veroanggra.sampleactivityembeding.data.News
import com.veroanggra.sampleactivityembeding.data.NewsData
import com.veroanggra.sampleactivityembeding.databinding.ActivityMainBinding
import com.veroanggra.sampleactivityembeding.details.DetailsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val newsAdapter by lazy { NewsAdapter(::openDetailScreenFor) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                binding.bottomNavigationView
            }
            else -> {
                binding.navigationRail
//                binding.bottomNavigationView

            }
        }

        newsAdapter.differ.submitList(NewsData.loadData())
        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsAdapter
        }
    }

    private fun openDetailScreenFor(data: News) {
        DetailsActivity.openDetailScreenFor(data, with = this)
    }
}
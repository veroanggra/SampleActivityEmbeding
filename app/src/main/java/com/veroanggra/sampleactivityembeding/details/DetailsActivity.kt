package com.veroanggra.sampleactivityembeding.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.veroanggra.sampleactivityembeding.FABProvider
import com.veroanggra.sampleactivityembeding.FABSplitListener
import com.veroanggra.sampleactivityembeding.data.News
import com.veroanggra.sampleactivityembeding.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity(), FABProvider {

    private lateinit var binding: ActivityDetailsBinding

    init {
        lifecycle.addObserver(FABSplitListener(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val letter = getLetter()
        binding.detailTextView.text = letter
    }

    private fun getLetter(): String {
        return intent.getStringExtra(EXTRA_LETTER)
            ?: throw IllegalStateException("Must pass a letter to DetailActivity")
    }

    // FABProvider
    override val fab: FloatingActionButton by lazy { binding.detailFAB }
    override val activity: AppCompatActivity = this

    companion object {
        private const val EXTRA_LETTER = "extra-letter"

        fun openDetailScreenFor(data: News, with: Context) {
            val intent = Intent(with, DetailsActivity::class.java)
            intent.putExtra(EXTRA_LETTER, data.title)
            with.startActivity(intent)
        }
    }
}

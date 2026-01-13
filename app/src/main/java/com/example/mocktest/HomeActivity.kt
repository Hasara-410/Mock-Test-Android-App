package com.example.mocktest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val pager = findViewById<ViewPager2>(R.id.pagerShow)

        val img1 = intent.getStringExtra("img1")?.let { Uri.parse(it) }
        val img2 = intent.getStringExtra("img2")?.let { Uri.parse(it) }
        val img3 = intent.getStringExtra("img3")?.let { Uri.parse(it) }

        pager.adapter = ShowcasePagerAdapter(listOf(img1, img2, img3))

        pager.offscreenPageLimit = 3
        pager.clipToPadding = false
        pager.clipChildren = false
        pager.isUserInputEnabled = true

        val rv = pager.getChildAt(0) as RecyclerView
        rv.clipToPadding = false
        rv.clipChildren = false
        rv.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // show side cards
        rv.setPadding(180, 0, 180, 0)

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(20))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.88f + (r * 0.12f)
            page.scaleX = 0.88f + (r * 0.12f)
            page.alpha = 0.55f + (r * 0.45f)
        }
        pager.setPageTransformer(transformer)

        findViewById<Button>(R.id.btnEdit).setOnClickListener {
            startActivity(Intent(this, WizardActivity::class.java))
            finish()
        }
    }
}

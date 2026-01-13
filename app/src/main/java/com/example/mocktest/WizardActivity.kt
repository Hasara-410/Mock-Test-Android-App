package com.example.mocktest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class WizardActivity : AppCompatActivity() {

    lateinit var pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wizard)

        pager = findViewById(R.id.pager)

        pager.isUserInputEnabled = false


        pager.offscreenPageLimit = 1

        pager.adapter = WizardAdapter(this)
    }

    fun next() {
        val count = pager.adapter?.itemCount ?: 0
        if (pager.currentItem < count - 1) pager.currentItem += 1
    }

    fun prev() {
        if (pager.currentItem > 0) pager.currentItem -= 1
    }

    fun goToHome() {
        startActivity(android.content.Intent(this, HomeActivity::class.java))
        finish()
    }
}

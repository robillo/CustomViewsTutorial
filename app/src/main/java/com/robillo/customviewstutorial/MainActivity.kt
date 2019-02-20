package com.robillo.customviewstutorial

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    companion object {
        private val paddingAmount = 30
    }

    private lateinit var myCustomView: MyCustomView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myCustomView = findViewById(R.id.mcv)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.increment_padding -> incrementPadding(paddingAmount)
            R.id.decrement_padding -> decrementPadding(paddingAmount)
            R.id.swap_color -> swapViewColor()
        }
    }

    private fun swapViewColor() {
        myCustomView.swapColor()
    }

    private fun incrementPadding(amount: Int) {
        myCustomView.paddingUp(amount)
    }

    private fun decrementPadding(amount: Int) {
        myCustomView.paddingDown(amount)
    }
}

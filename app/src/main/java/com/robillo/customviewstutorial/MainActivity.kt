package com.robillo.customviewstutorial

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

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
        val isChanged = myCustomView.paddingUp(amount)
        if(!isChanged)
            showSnackBar(getString(R.string.bounds_exceeding))
    }

    private fun decrementPadding(amount: Int) {
        val isChanged = myCustomView.paddingDown(amount)
        if(!isChanged)
            showSnackBar(getString(R.string.bounds_exceeding))
    }

    private fun showSnackBar(text: String) {
        val snackbar = Snackbar.make(coordinatorLayout, text, 1000)
        val view = snackbar.getView()
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.red_color))
        snackbar.show()
    }
}

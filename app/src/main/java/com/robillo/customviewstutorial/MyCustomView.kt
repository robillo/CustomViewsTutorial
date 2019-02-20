package com.robillo.customviewstutorial

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class MyCustomView : View {

    private lateinit var mPaint: Paint
    private lateinit var mRect: Rect

    companion object {
        private var mSquareColor: Int = 0
        private var mPadding = 0
        private val originX = 0
        private val originY = 0
    }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(set: AttributeSet?) {
        if (set == null) return

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mRect = Rect()

        val typedArray = context.obtainStyledAttributes(set, R.styleable.MyCustomView)
        mSquareColor = typedArray.getColor(R.styleable.MyCustomView_square_color, Color.GREEN)
        mPaint.color = mSquareColor
        typedArray.recycle()
    }

    fun swapColor() {
        mPaint.color = if (mPaint.color == mSquareColor) Color.RED else mSquareColor
        postInvalidate()
    }

    fun paddingUp(padding: Int) {
        mPadding += padding
        refresh()
    }

    fun paddingDown(padding: Int) {
        mPadding -= padding
        refresh()
    }

    private fun refresh() {
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        setViewBounds()
        canvas.drawRect(mRect, mPaint)
    }

    private fun setViewBounds() {
        mRect.left = originX + mPadding
        mRect.right = width - mPadding
        mRect.top = originY + mPadding
        mRect.bottom = height - mPadding
    }
}

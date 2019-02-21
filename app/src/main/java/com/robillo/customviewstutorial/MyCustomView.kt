package com.robillo.customviewstutorial

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class MyCustomView : View {

    private val VIEW_CHANGED = true
    private val VIEW_NOT_CHANGED = false
    private val DEFAULT_COLOR = Color.DKGRAY

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
        mSquareColor = typedArray.getColor(R.styleable.MyCustomView_square_color, DEFAULT_COLOR)
        mPaint.color = mSquareColor
        typedArray.recycle()
    }

    fun swapColor() {
        mPaint.color = if (mPaint.color == mSquareColor) Color.CYAN else mSquareColor
        postInvalidate()
    }

    fun paddingUp(padding: Int): Boolean {
        if(isPaddingWithinBounds(mPadding + padding)) {
            mPadding += padding
            refresh()
            return VIEW_CHANGED
        }
        return VIEW_NOT_CHANGED
    }

    fun paddingDown(padding: Int): Boolean {
        if(isPaddingWithinBounds(mPadding - padding)) {
            mPadding -= padding
            refresh()
            return VIEW_CHANGED
        }
        return VIEW_NOT_CHANGED
    }

    private fun isPaddingWithinBounds(padding: Int): Boolean {
        return checkBoundsForPaddingUp(padding) && checkBoundsForPaddingDown(padding)
    }

    private fun checkBoundsForPaddingUp(padding: Int): Boolean {
        return (originX + padding < width - padding) && (originY + padding < height - padding)
    }

    private fun checkBoundsForPaddingDown(padding: Int): Boolean {
        return (padding > 0)
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

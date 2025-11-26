package br.unip.hermesbot.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class LineChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private var values: List<Int> = listOf()
    private val linePaint = Paint().apply { color = Color.BLUE; strokeWidth = 6f; isAntiAlias = true }
    private val pointPaint = Paint().apply { color = Color.RED; style = Paint.Style.FILL; isAntiAlias = true }
    private val axisPaint = Paint().apply { color = Color.GRAY; strokeWidth = 3f }

    fun setData(data: List<Int>) {
        values = data
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (values.isEmpty()) return

        val widthStep = width / (values.size + 1).toFloat()
        val maxVal = (values.maxOrNull() ?: 1).toFloat()
        val minVal = (values.minOrNull() ?: 0).toFloat()
        val range = (maxVal - minVal).coerceAtLeast(1f)

        canvas.drawLine(0f, height - 40f, width.toFloat(), height - 40f, axisPaint)

        var prevX = 0f
        var prevY = 0f

        values.forEachIndexed { i, v ->
            val x = (i + 1) * widthStep
            val y = height - 40f - ((v - minVal) / range * (height - 100f))
            canvas.drawCircle(x, y, 8f, pointPaint)
            if (i > 0) canvas.drawLine(prevX, prevY, x, y, linePaint)
            prevX = x
            prevY = y
        }
    }
}

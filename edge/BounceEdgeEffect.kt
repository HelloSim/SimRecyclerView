package com.sim.commonui.widget.recyclerview

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.view.animation.DecelerateInterpolator
import android.widget.EdgeEffect
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.EdgeEffectFactory.DIRECTION_BOTTOM
import androidx.recyclerview.widget.RecyclerView.EdgeEffectFactory.DIRECTION_TOP

class BounceEdgeEffect(
    private val recyclerView: RecyclerView,
    private val direction: Int
) : EdgeEffect(recyclerView.context) {

    private var totalOffset = 0f
    private val maxOffset = recyclerView.resources.displayMetrics.density * 120

    override fun onPull(deltaDistance: Float) {
        super.onPull(deltaDistance)
        handlePull(deltaDistance)
    }

    override fun onPull(deltaDistance: Float, displacement: Float) {
        super.onPull(deltaDistance, displacement)
        handlePull(deltaDistance)
    }

    override fun onRelease() {
        super.onRelease()
        if (totalOffset != 0f) {
            recover()
        }
    }

    private fun handlePull(delta: Float) {
        if (direction == DIRECTION_TOP && recyclerView.canScrollVertically(-1)) return
        if (direction == DIRECTION_BOTTOM && recyclerView.canScrollVertically(1)) return

        val sign = if (direction == DIRECTION_BOTTOM) -1 else 1
        val deltaOffset = sign * recyclerView.height * delta * 0.35f

        val newOffset = (totalOffset + deltaOffset).coerceIn(-maxOffset, maxOffset)

        val realOffset = newOffset - totalOffset
        totalOffset = newOffset

        offsetChildren(realOffset)
    }

    private fun offsetChildren(offset: Float) {
        val childCount = recyclerView.childCount
        for (i in 0 until childCount) {
            recyclerView.getChildAt(i).translationY += offset
        }
    }

    private fun recover() {
        val animator = ValueAnimator.ofFloat(totalOffset, 0f)
        animator.duration = 300
        animator.interpolator = DecelerateInterpolator()
        animator.addUpdateListener {
            val value = it.animatedValue as Float
            val delta = value - totalOffset
            totalOffset = value
            offsetChildren(delta)
        }
        animator.start()
    }

    override fun draw(canvas: Canvas): Boolean = false

    /*recyclerview.edgeEffectFactory = object : RecyclerView.EdgeEffectFactory() {
        override fun createEdgeEffect(
            recyclerView: RecyclerView,
            direction: Int
        ): EdgeEffect {
            return BounceEdgeEffect(recyclerView, direction)
        }
    }*/
}

package com.sim.commonui.widget.recyclerview.animators

import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.RecyclerView

open class OvershootInLeftAnimator : BaseItemAnimator {
  private val tension: Float

  constructor() {
    tension = 2.0f
  }

  constructor(tension: Float) {
    this.tension = tension
  }

  override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate().apply {
      translationX(-holder.itemView.rootView.width.toFloat())
      duration = removeDuration
      setListener(DefaultRemoveAnimatorListener(holder))
      startDelay = getRemoveDelay(holder)
    }.start()
  }

  override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.translationX = -holder.itemView.rootView.width.toFloat()
  }

  override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate().apply {
      translationX(0f)
      duration = addDuration
      setListener(DefaultAddAnimatorListener(holder))
      interpolator = OvershootInterpolator(tension)
      startDelay = getAddDelay(holder)
    }.start()
  }
}

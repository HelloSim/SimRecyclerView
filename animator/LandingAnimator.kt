package com.sim.commonui.widget.recyclerview.animators

import android.view.animation.Interpolator
import androidx.recyclerview.widget.RecyclerView

open class LandingAnimator : BaseItemAnimator {
  constructor()
  constructor(interpolator: Interpolator) {
    this.interpolator = interpolator
  }

  override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate().apply {
      alpha(0f)
        .scaleX(1.5f)
        .scaleY(1.5f)
      duration = removeDuration
      interpolator = interpolator
      setListener(DefaultRemoveAnimatorListener(holder))
      startDelay = getRemoveDelay(holder)
    }.start()
  }

  override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.alpha = 0f
    holder.itemView.scaleX = 1.5f
    holder.itemView.scaleY = 1.5f
  }

  override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate().apply {
      alpha(1f)
      scaleX(1f)
      scaleY(1f)
      duration = addDuration
      interpolator = interpolator
      setListener(DefaultAddAnimatorListener(holder))
      startDelay = getAddDelay(holder)
    }.start()
  }
}

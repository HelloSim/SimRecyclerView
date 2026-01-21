package com.sim.commonui.widget.recyclerview.animators

import android.view.animation.Interpolator
import androidx.recyclerview.widget.RecyclerView

open class FlipInRightYAnimator : BaseItemAnimator {
  constructor()
  constructor(interpolator: Interpolator) {
    this.interpolator = interpolator
  }

  override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate().apply {
      rotationY(-90f)
      duration = removeDuration
      interpolator = interpolator
      setListener(DefaultRemoveAnimatorListener(holder))
      startDelay = getRemoveDelay(holder)
    }.start()
  }

  override fun preAnimateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.rotationY = -90f
  }

  override fun animateAddImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate().apply {
      rotationY(0f)
      duration = addDuration
      interpolator = interpolator
      setListener(DefaultAddAnimatorListener(holder))
      startDelay = getAddDelay(holder)
    }.start()
  }
}

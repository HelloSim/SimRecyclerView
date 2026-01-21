package com.sim.commonui.widget.recyclerview.animators

import android.view.animation.Interpolator
import androidx.recyclerview.widget.RecyclerView

open class SlideInLeftAnimator : BaseItemAnimator {
  constructor()
  constructor(interpolator: Interpolator) {
    this.interpolator = interpolator
  }

  override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
    holder.itemView.animate().apply {
      translationX(-holder.itemView.rootView.width.toFloat())
      duration = removeDuration
      interpolator = interpolator
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
      interpolator = interpolator
      setListener(DefaultAddAnimatorListener(holder))
      startDelay = getAddDelay(holder)
    }.start()
  }
}

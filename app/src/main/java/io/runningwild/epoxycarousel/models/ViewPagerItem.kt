package io.runningwild.epoxycarousel.models

import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import io.runningwild.epoxycarousel.R

@EpoxyModelClass(layout = R.layout.model_item_view_pager)
abstract class ViewPagerItem : EpoxyModelWithHolder<ViewPagerItem.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.textTitle.text = title
    }

    class Holder : BaseEpoxyHolder() {
        val textTitle: TextView by bind(R.id.textTitle)
    }
}

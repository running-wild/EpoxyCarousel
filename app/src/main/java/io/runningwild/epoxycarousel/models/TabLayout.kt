package io.runningwild.epoxycarousel.models

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import io.runningwild.epoxycarousel.R
import timber.log.Timber

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TabLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        addView(LinearLayout(context).also {
            val params = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            params.gravity = Gravity.CENTER_HORIZONTAL
            it.layoutParams = params
            it.orientation = HORIZONTAL
        })
    }

    private var checkedIndex = 0
    private var checkedView: CheckBox? = null

    @ModelProp
    fun setCount(count: Int) {
        (getChildAt(0) as ViewGroup).let {
            when {
                // Add
                count > it.childCount -> for (i in 0 until (count - it.childCount)) {
                    View.inflate(context, R.layout.model_item_tab_layout, it)
                }
                // Remove
                count < it.childCount -> for (i in 0 until (it.childCount - count)) {
                    it.removeViewAt(0)
                }
            }
        }
        if (checkedView == null) {
            setChecked(checkedIndex)
        } else {
            setChecked(0)
        }
    }

    @SuppressLint("BinaryOperationInTimber")
    @ModelProp
    fun setChecked(index: Int) {
        checkedIndex = index
        (getChildAt(0) as ViewGroup).let {
            when {
                index < it.childCount -> {
                    checkedView?.let { checkBox ->
                        checkBox.isChecked = false
                    }
                    checkedView = (it.getChildAt(index) as? CheckBox)?.also { checkBox ->
                        checkBox.isChecked = true
                    }
                }
                else -> {
                    Timber.w("Unable to set tab checked at index={$index}; " +
                            "will try again once the tab views are initialized.")
                }
            }
        }
    }
}

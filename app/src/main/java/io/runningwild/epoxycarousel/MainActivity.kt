package io.runningwild.epoxycarousel

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyVisibilityTracker
import io.runningwild.epoxycarousel.controller.ViewPagerController
import io.runningwild.epoxycarousel.controller.data.ViewPagerData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.setController(ViewPagerController().also {
            it.setData(ViewPagerData(resources.getStringArray(R.array.view_pager_items), 0))
        })

        // Attach visibility tracker to the RecyclerView to enable visibility events.
        EpoxyVisibilityTracker().attach(viewPager)
        Carousel.setDefaultGlobalSnapHelperFactory(object : Carousel.SnapHelperFactory() {
            override fun buildSnapHelper(context: Context?): SnapHelper {
                return PagerSnapHelper()
            }
        })
    }
}

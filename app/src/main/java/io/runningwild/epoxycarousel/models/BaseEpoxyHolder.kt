package io.runningwild.epoxycarousel.models

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A pattern for easier view binding with an [EpoxyHolder]
 *
 * @see <a href="https://github.com/airbnb/epoxy">airbnb/epoxy</a>
 */
abstract class BaseEpoxyHolder : EpoxyHolder() {
    private lateinit var view: View

    override fun bindView(itemView: View) {
        view = itemView
    }

    protected fun <V : View> bind(id: Int): ReadOnlyProperty<BaseEpoxyHolder, V> =
            Lazy { holder: BaseEpoxyHolder, prop ->
                holder.view.findViewById(id) as V?
                        ?: throw IllegalStateException("View ID $id for '${prop.name}' not found.")
            }

    /**
     * Taken from kotterknife.
     *
     * @see <a href="https://github.com/JakeWharton/kotterknife">JakeWharton/kotterknife</a>
     */
    private class Lazy<V>(
            private val initializer: (BaseEpoxyHolder, KProperty<*>) -> V
    ) : ReadOnlyProperty<BaseEpoxyHolder, V> {
        private object EMPTY

        private var value: Any? = EMPTY

        override fun getValue(thisRef: BaseEpoxyHolder, property: KProperty<*>): V {
            if (value == EMPTY) {
                value = initializer(thisRef, property)
            }
            @Suppress("UNCHECKED_CAST")
            return value as V
        }
    }
}

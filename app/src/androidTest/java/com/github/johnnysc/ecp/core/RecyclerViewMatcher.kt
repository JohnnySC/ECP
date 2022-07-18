package com.github.johnnysc.ecp.core

import android.content.res.Resources
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import com.github.johnnysc.ecp.R
import kotlin.math.log

class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int, targetViewId: Int = -1) = atPositionOnView(position, targetViewId)

    private fun atPositionOnView(position: Int, targetViewId: Int) =
        object : TypeSafeMatcher<View>() {
            var resources: Resources? = null
            var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = Integer.toString(recyclerViewId)
                if (this.resources != null) {
                    idDescription = try {
                        this.resources!!.getResourceName(recyclerViewId)
                    } catch (e: Resources.NotFoundException) {
                        String.format("%s (resource name not found)", recyclerViewId)
                    }
                }

                description.appendText("RecyclerView with id: $idDescription at position: $position")
            }

            override fun matchesSafely(view: View): Boolean {

                this.resources = view.resources

                if (childView == null) {
                    val recyclerView = view.rootView.findViewById(recyclerViewId) as RecyclerView
                    if (recyclerView.id == recyclerViewId) {
                        val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                        if (viewHolder != null) {
                            childView = viewHolder.itemView.findViewById(R.id.messageTextView)
                        }
                    } else {
                        return false
                    }
                }

                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView = childView!!.findViewById<View>(targetViewId)
                    view === targetView
                }
            }
        }
}
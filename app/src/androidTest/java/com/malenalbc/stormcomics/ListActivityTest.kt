package com.malenalbc.stormcomics

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.malenalbc.stormcomics.presentation.list.ListActivity
import com.malenalbc.stormcomics.server.MockServerDefaultDispatcher
import com.malenalbc.stormcomics.server.MockServerTest
import com.malenalbc.stormcomics.utils.itemViewMatches
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumentation test for [ListActivity]
 */
@RunWith(AndroidJUnit4::class)
class ListActivityTest : MockServerTest() {
    @get:Rule
    var activityRule: ActivityTestRule<ListActivity> = ActivityTestRule(ListActivity::class.java, false, false)

    @Test
    fun testLoadMainActivity() {
        webServer.dispatcher = MockServerDefaultDispatcher().RequestDispatcher()

        activityRule.launchActivity(Intent())

        onView(withId(R.id.fab)).check(matches((isDisplayed())))
        onView(withId(R.id.item_list)).
            check(itemViewMatches(0, R.id.title, withText(containsString("Wolverine & the X-Men (2011) #20"))))

    }
}

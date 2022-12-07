package com.UGD.kurirku


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class registerTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(register::class.java)

    @Test
    fun registerTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.btnRegister), withText("Register"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText = onView(
            allOf(
                withId(R.id.etNama),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilNama),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("Kurirku"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.etEmail),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilEmail),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("briantana@gmail.com"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.etNoHandphone),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilNoHandphone),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(click())
        onView(isRoot()).perform(waitFor(3000))

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.etNoHandphone),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilNoHandphone),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("085975389100"), closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.etPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilPassword),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(replaceText("bebek"), closeSoftKeyboard())

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.etTanggalLahir),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilTanggalLahir),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(replaceText("19 Mei 2002"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.btnRegister), withText("Register"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
        onView(isRoot()).perform(waitFor(3000))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}

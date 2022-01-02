package com.example.kotlinperformantieapp

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.android.synthetic.main.activity_hoofdscherm.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.containsString
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.system.measureTimeMillis


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UITestCrypto {

    @get:Rule
    var activityStartRule: ActivityScenarioRule<Hoofdscherm> =
        ActivityScenarioRule(Hoofdscherm::class.java)

    @Test
    fun ActivityStartTest(){
        val scenario = activityStartRule.scenario
        Thread.sleep(2000)
        scenario.onActivity { activity ->
            activity.netwerkButton.performClick()
        }
        Thread.sleep(2000)
        onView(withId(R.id.llNavScr)).check(matches(isDisplayed()))
        onView(withId(R.id.cyptoButton)).check(matches(isDisplayed()))
        val btn = onView(withId(R.id.cyptoButton))
        val totalExecutionTime = measureTimeMillis {
            btn.perform(click())
            while(tryOnView()){

            }
        }
        println("Total Execution Time: $totalExecutionTime")
    }

    private fun tryOnView(): Boolean {
        var x = true;
        try{
            val tekst = getText(withId(R.id.textCrypto))
            if(tekst.isNullOrBlank()){
                println("Twerkte niet")
            }else if(tekst.isNotEmpty()){
                onView(withId(R.id.textCrypto)).check(matches(withText(containsString("symbol"))))
                x = false;
                println("Twerkte")
            }
        }catch (e: Exception){

        }
        return x;
    }

    fun getText(matcher: Matcher<View?>?): String? {
        val stringHolder = arrayOf<String?>(null)
        onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController?, view: View) {
                val tv = view as TextView //Save, because of check in getConstraints()
                stringHolder[0] = tv.text.toString()
            }
        })
        return stringHolder[0]
    }


}
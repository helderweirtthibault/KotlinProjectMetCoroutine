package com.example.kotlinperformantieapp

import android.app.Activity
import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import kotlinx.android.synthetic.main.activity_hoofdscherm.*
import kotlinx.android.synthetic.main.activity_maps.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers
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
class UITest2 {

    var t = "50 3"

    @get:Rule
    var activityStartRule: ActivityScenarioRule<Hoofdscherm> =
        ActivityScenarioRule(Hoofdscherm::class.java)


    @Test
    fun ActivityStartTest(){
        val scenario = activityStartRule.scenario
        Thread.sleep(2000)
        //test changeCourse
        val totalExecutionTime = measureTimeMillis {
            scenario.onActivity { activity ->
                activity.mapsButton.performClick()
            }
            while(textIsNotChanged()){

            }
        }
        println("Total Execution Time: $totalExecutionTime")
    }

    private fun textIsNotChanged(): Boolean {
        var x = true
        try{
            val tekst = getText(withId(R.id.mapPositie))
            if (tekst != null) {
                if(tekst.isNotBlank()){
                    if(tekst.equals(t)){
                        println("Lukte")
                        x = false;
                    }
                }
            }
        }catch (e: Exception){

        }
        if(x){
            println("Lukte niet")
        }
        return x
    }

    private fun getText(matcher: Matcher<View?>?): String? {
        val stringHolder = arrayOf<String?>(null)
        Espresso.onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(TextView::class.java)
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
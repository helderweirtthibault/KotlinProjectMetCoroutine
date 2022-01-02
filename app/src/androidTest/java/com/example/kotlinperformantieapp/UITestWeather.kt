package com.example.kotlinperformantieapp

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.android.synthetic.main.activity_hoofdscherm.*
import org.hamcrest.Matchers.not
import kotlinx.coroutines.delay
import org.hamcrest.Matcher
import org.hamcrest.Matchers.containsString

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.util.logging.Logger
import kotlin.system.measureTimeMillis

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UITestWeather {

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
        onView(withId(R.id.fragment_nav_screen)).check(matches(isDisplayed()))
        onView(withId(R.id.apiCoButton)).check(matches(isDisplayed()))

        val btn = onView(withId(R.id.apiCoButton))

        val totalExecutionTime = measureTimeMillis {
            btn.perform(click())
            while(tryOnView()){

            }
        }
        //            if(withId(R.id.textCall1).matches(withText(""))){
//                while(withId(R.id.textCall1).matches(withText(""))){
//                    onView(withId(R.id.textCall1)).check(matches(withText("{\"coord\":{\"lon\":-0.1257,\"lat\":51.5085},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":279.5,\"feels_like\":277.22,\"temp_min\":278.07,\"temp_max\":280.43,\"pressure\":1028,\"humidity\":77},\"visibility\":10000,\"wind\":{\"speed\":3.09,\"deg\":50},\"clouds\":{\"all\":90},\"dt\":1640018434,\"sys\":{\"type\":2,\"id\":2019646,\"country\":\"GB\",\"sunrise\":1639987402,\"sunset\":1640015574},\"timezone\":0,\"id\":2643743,\"name\":\"London\",\"cod\":200}")));
//                }
//            }else{
//                onView(withId(R.id.textCall1)).check(matches(withText("{\"coord\":{\"lon\":-0.1257,\"lat\":51.5085},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":279.5,\"feels_like\":277.22,\"temp_min\":278.07,\"temp_max\":280.43,\"pressure\":1028,\"humidity\":77},\"visibility\":10000,\"wind\":{\"speed\":3.09,\"deg\":50},\"clouds\":{\"all\":90},\"dt\":1640018434,\"sys\":{\"type\":2,\"id\":2019646,\"country\":\"GB\",\"sunrise\":1639987402,\"sunset\":1640015574},\"timezone\":0,\"id\":2643743,\"name\":\"London\",\"cod\":200}")));
//            }
//
//            if(withId(R.id.textCall2).matches(withText(""))){
//                while(withId(R.id.textCall2).matches(withText(""))){
//                    onView(withId(R.id.textCall2)).check(matches(withText("{\"location\":{\"name\":\"Paris\",\"region\":\"Ile-de-France\",\"country\":\"France\",\"lat\":48.87,\"lon\":2.33,\"tz_id\":\"Europe/Paris\",\"localtime_epoch\":1640018628,\"localtime\":\"2021-12-20 17:43\"},\"current\":{\"last_updated_epoch\":1640017800,\"last_updated\":\"2021-12-20 17:30\",\"temp_c\":5.0,\"temp_f\":41.0,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":10.5,\"wind_kph\":16.9,\"wind_degree\":50,\"wind_dir\":\"NE\",\"pressure_mb\":1025.0,\"pressure_in\":30.27,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":75,\"cloud\":75,\"feelslike_c\":2.4,\"feelslike_f\":36.2,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":3.0,\"gust_mph\":9.2,\"gust_kph\":14.8}}")));
//                }
//            }else{
//                onView(withId(R.id.textCall2)).check(matches(withText("{\"location\":{\"name\":\"Paris\",\"region\":\"Ile-de-France\",\"country\":\"France\",\"lat\":48.87,\"lon\":2.33,\"tz_id\":\"Europe/Paris\",\"localtime_epoch\":1640018628,\"localtime\":\"2021-12-20 17:43\"},\"current\":{\"last_updated_epoch\":1640017800,\"last_updated\":\"2021-12-20 17:30\",\"temp_c\":5.0,\"temp_f\":41.0,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":10.5,\"wind_kph\":16.9,\"wind_degree\":50,\"wind_dir\":\"NE\",\"pressure_mb\":1025.0,\"pressure_in\":30.27,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":75,\"cloud\":75,\"feelslike_c\":2.4,\"feelslike_f\":36.2,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":3.0,\"gust_mph\":9.2,\"gust_kph\":14.8}}")));
//            }


            //onView(withId(R.id.textCall1)).check(matches(withText("{\"coord\":{\"lon\":-0.1257,\"lat\":51.5085},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":279.5,\"feels_like\":277.22,\"temp_min\":278.07,\"temp_max\":280.43,\"pressure\":1028,\"humidity\":77},\"visibility\":10000,\"wind\":{\"speed\":3.09,\"deg\":50},\"clouds\":{\"all\":90},\"dt\":1640018434,\"sys\":{\"type\":2,\"id\":2019646,\"country\":\"GB\",\"sunrise\":1639987402,\"sunset\":1640015574},\"timezone\":0,\"id\":2643743,\"name\":\"London\",\"cod\":200}")));

   //         println(withId(R.id.textCall2))
     //       println(onView(withId(R.id.textCall2)).check(matches(("{\"location\":{\"name\":\"Paris\",\"region\":\"Ile-de-France\",\"country\":\"France\",\"lat\":48.87,\"lon\":2.33,\"tz_id\":\"Europe/Paris\",\"localtime_epoch\":1640018628,\"localtime\":\"2021-12-20 17:43\"},\"current\":{\"last_updated_epoch\":1640017800,\"last_updated\":\"2021-12-20 17:30\",\"temp_c\":5.0,\"temp_f\":41.0,\"is_day\":0,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/116.png\",\"code\":1003},\"wind_mph\":10.5,\"wind_kph\":16.9,\"wind_degree\":50,\"wind_dir\":\"NE\",\"pressure_mb\":1025.0,\"pressure_in\":30.27,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":75,\"cloud\":75,\"feelslike_c\":2.4,\"feelslike_f\":36.2,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":3.0,\"gust_mph\":9.2,\"gust_kph\":14.8}}"))));
        println("Total Execution Time: $totalExecutionTime")
    }


    private fun tryOnView(): Boolean {
        var x = true
        try{
            val tekst = getText(withId(R.id.textCoCall1))
            val tekst2 = getText(withId(R.id.textCoCall2))
            if (!tekst.isNullOrBlank() && !tekst2.isNullOrBlank()) {
                if (tekst != null) {
                    if (tekst2 != null) {
                        if(tekst.contains("coord") && tekst2.contains("location")){
                            println("Lukte")
                            x = false;
                        }
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
package com.pivoto.codeassessment.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario

/**
 * It is impossible to use jetpack Fragment Scenario with Hilt because their empty activity is not
 * annotated with "AndroidEntryPoint". This custom Fragment Scenario then, supports hilt
 */
class  FragmentScenario<T: Fragment> (private val fragmentClass: Class<T>){

    private lateinit var fragment: T
    private lateinit var activityScenario: ActivityScenario<TestActivity>

    internal fun launchInternal(args: Bundle? = null) {
        activityScenario = ActivityScenario.launch(TestActivity::class.java)
        activityScenario.onActivity { activity ->
            @Suppress("UNCHECKED_CAST")
            fragment = activity.supportFragmentManager.fragmentFactory.instantiate(fragmentClass.classLoader!!, fragmentClass.name) as T

            args?.let { fragment.arguments = it }

            activity.supportFragmentManager.beginTransaction()
                .add(android.R.id.content, fragment, fragmentClass.name)
                .commitNow()
        }
    }
}

fun <T : Fragment> launch(fragmentClass: Class<T>, args: Bundle? = null): FragmentScenario<T> {
    return FragmentScenario(fragmentClass).apply { launchInternal(args) }
}

package com.pivoto.codeassessment.features.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pivoto.codeassessment.test.launch
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@Config(manifest = "AndroidManifest.xml", sdk = [33], application = HiltTestApplication::class)
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun initialStateSuccess_01() {
        // When
        launch(HomeFragment::class.java)

        // Then
        onView(withId(R.id.photo_title)).check(ViewAssertions.matches(withText("Title")))
    }
}

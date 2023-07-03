package com.pivoto.codeassessment.navigation

import android.os.Bundle
import android.os.Parcelable

/**
 * Result of a navigation (used in the Fragment Result API)
 */
interface NavigationResult: Parcelable {

    /**
     * Extras produced as result of the navigation
     */
    fun getArgsBundle(): Bundle?
}

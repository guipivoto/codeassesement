package com.pivoto.codeassessment.navigation

import android.os.Bundle

/**
 * Describe a destination from the NavGraph and can be used to navigate to another screen
 */
interface Destination {

    /**
     * Id of the destination in the nav graph
     */
    val destinationId: Int

    /**
     * Key that the destination will use to produce fragment results
     */
    val resultListenerKey: String

    /**
     * Extras that should be passed to the destination so it can start properly
     */
    fun getArgsBundle(): Bundle?
}

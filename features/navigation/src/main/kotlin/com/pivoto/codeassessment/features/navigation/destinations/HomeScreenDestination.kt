package com.pivoto.codeassessment.features.navigation.destinations

import android.os.Bundle
import com.pivoto.codeassessment.features.navigation.R
import com.pivoto.codeassessment.navigation.Destination

object HomeScreenDestination : Destination {

    override val destinationId: Int
        get() = R.id.home_screen_destination_id

    override val resultListenerKey: String
        get() = "home_screen_result"

    override fun getArgsBundle(): Bundle? = null
}

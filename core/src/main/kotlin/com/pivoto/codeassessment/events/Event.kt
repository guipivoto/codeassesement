package com.pivoto.codeassessment.events

import com.pivoto.codeassessment.navigation.Destination

/**
 * Single time events that can be produced by a View Model
 */
sealed interface Event {

    data class Navigate(val destination: Destination): Event
}

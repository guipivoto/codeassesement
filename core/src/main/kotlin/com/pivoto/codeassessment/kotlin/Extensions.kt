package com.pivoto.codeassessment.kotlin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

/**
 * Execute a suspend block on the IO context and wait its result without blocking the current thread
 * @param block Suspend block that should be invoked in the IO context
 */
suspend fun <T> onBackground(block: suspend () -> T): T =
    CoroutineScope(Dispatchers.IO).async {
        return@async block()
    }.await()

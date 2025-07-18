package com.pjb.todo.todobackend.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

fun <T>CoroutineScope.asyncIO(
    block: suspend CoroutineScope.() -> T
) = async(Dispatchers.IO) { block() }

fun <T>CoroutineScope.asyncMAIN(
    block: suspend CoroutineScope.() -> T
) = async(Dispatchers.Main) { block() }
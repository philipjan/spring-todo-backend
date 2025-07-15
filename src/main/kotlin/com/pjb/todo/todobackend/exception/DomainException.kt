package com.pjb.todo.todobackend.exception

open class DomainException(
    @Transient open val statusCode: Int? = null,
    override val message: String? = null,
    @Transient open val stackTrace: String? = null,
    open val internalCode: String
): RuntimeException(message)
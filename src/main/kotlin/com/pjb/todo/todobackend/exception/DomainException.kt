package com.pjb.todo.todobackend.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

open class DomainException(
    @Transient open val statusCode: Int? = null,
    override val message: String? = null,
    @Transient open val stackTrace: String? = null,
    open val internalCode: String
): RuntimeException(message)

@ResponseStatus(HttpStatus.NOT_FOUND)
data class NotFoundException(
    val internalCodeValue: String
): DomainException(message = internalCodeValue, internalCode = internalCodeValue)

@ResponseStatus(HttpStatus.BAD_REQUEST)
data class BadRequestException(
    val internalCodeValue: String
): DomainException(message = internalCodeValue, internalCode = internalCodeValue)
package com.pjb.todo.todobackend.model

import com.pjb.todo.todobackend.exception.DomainException


sealed class Response<T>( var data: T? = null, var error: DomainException? = null) {

    data class Success<T>(val content: T? = null) : Response<T>(data = content)

    data class Error(val errorData: DomainException? = null): Response<DomainException>(error = errorData)
}

data class ApiResponse<T>(
    val data: T,
    val errorCode: String? = null,
    val errorData: DomainException? = null
)
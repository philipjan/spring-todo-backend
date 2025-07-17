package com.pjb.todo.todobackend.model.dto

import jakarta.validation.constraints.NotBlank

data class TodoDTO(
    val id: Long? = null,
    @field:NotBlank val task: String,
    val isFinished: Boolean = false
)

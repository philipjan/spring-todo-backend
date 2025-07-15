package com.pjb.todo.todobackend.service

import com.pjb.todo.todobackend.repository.todo.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    fun findAll() = todoRepository.findAll()
}
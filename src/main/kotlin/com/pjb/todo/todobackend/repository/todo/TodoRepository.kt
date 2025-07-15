package com.pjb.todo.todobackend.repository.todo

import com.pjb.todo.todobackend.model.entity.todo.Todo
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TodoRepository : CoroutineCrudRepository<Todo, Long>
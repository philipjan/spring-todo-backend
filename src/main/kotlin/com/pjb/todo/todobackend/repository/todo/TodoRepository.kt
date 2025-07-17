package com.pjb.todo.todobackend.repository.todo

import com.pjb.todo.todobackend.model.entity.todo.Todo
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TodoRepository : ReactiveCrudRepository<Todo, Long>
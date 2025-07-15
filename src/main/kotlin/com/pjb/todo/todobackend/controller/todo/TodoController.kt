package com.pjb.todo.todobackend.controller.todo

import com.pjb.todo.todobackend.service.TodoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("/todo")
    fun getAll() = todoService.findAll()
}
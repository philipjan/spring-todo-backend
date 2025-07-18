package com.pjb.todo.todobackend.controller.todo

import com.pjb.todo.todobackend.model.dto.TodoDTO
import com.pjb.todo.todobackend.service.TodoService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("/todo")
    fun getAll() = todoService.findAll()

    @GetMapping("/todo/{id}")
     suspend fun getTodoById(@PathVariable id: Long) = todoService.getTodoById(id)

    @PostMapping("/todo")
    suspend fun createTodo(@Valid @RequestBody todoDTO: TodoDTO) = todoService.createTodo(todoDTO)

    @PutMapping("/todo/{id}")
    suspend fun updateTodo(@PathVariable id: Long, @Valid @RequestBody payload: TodoDTO) = todoService.updateTodo(
        id = id,
        payload = payload
    )

    @DeleteMapping("/todo/{id}")
    suspend fun deleteTodo(@PathVariable id: Long) = todoService.deleteTodo(id)
}
package com.pjb.todo.todobackend.controller.todo

import com.pjb.todo.todobackend.model.dto.TodoDTO
import com.pjb.todo.todobackend.service.TodoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todo")
class TodoController(
    private val todoService: TodoService
) {

    @GetMapping("","/")
    fun getAll() = todoService.findAll()

    @GetMapping("/{id}")
     suspend fun getTodoById(@PathVariable id: Long) = todoService.getTodoById(id)

    @PostMapping("","/")
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun createTodo(@Valid @RequestBody todoDTO: TodoDTO) = todoService.createTodo(todoDTO)

    @PutMapping("/{id}")
    suspend fun updateTodo(@PathVariable id: Long, @Valid @RequestBody payload: TodoDTO) = todoService.updateTodo(
        id = id,
        payload = payload
    )

    @DeleteMapping("/{id}")
    suspend fun deleteTodo(@PathVariable id: Long) = todoService.deleteTodo(id)
}
package com.pjb.todo.todobackend.service

import com.pjb.todo.todobackend.exception.BadRequestException
import com.pjb.todo.todobackend.exception.NotFoundException
import com.pjb.todo.todobackend.exception.TodoErrorCodes
import com.pjb.todo.todobackend.model.dto.TodoDTO
import com.pjb.todo.todobackend.model.entity.todo.Todo
import com.pjb.todo.todobackend.repository.todo.TodoRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    fun findAll() = todoRepository.findAll()

    fun getTodoById(id: Long) = todoRepository.findById(id).switchIfEmpty {
        Mono.error(NotFoundException(TodoErrorCodes.ERR_TODO_NOT_FOUND))
    }

    fun createTodo(todoDTO: TodoDTO) = todoRepository.save<Todo>(
        Todo(
            id = todoDTO.id,
            task = todoDTO.task,
            isFinished = todoDTO.isFinished
        )
    )

    fun updateTodo(id: Long, payload: TodoDTO) = todoRepository.findById(id)
        .switchIfEmpty {
            Mono.error(BadRequestException(TodoErrorCodes.ERR_TODO_BAD_REQUEST))
        }
        .flatMap {
            createTodo(
                TodoDTO(id = id, task = payload.task, isFinished = payload.isFinished)
            )
        }

    fun deleteTodo(id: Long) = todoRepository.findById(id)
        .switchIfEmpty {
            Mono.error(BadRequestException(TodoErrorCodes.ERR_TODO_BAD_REQUEST))
        }
        .flatMap {
            todoRepository.deleteById(id)
        }
}
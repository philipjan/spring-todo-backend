package com.pjb.todo.todobackend.service

import com.pjb.todo.todobackend.exception.BadRequestException
import com.pjb.todo.todobackend.exception.NotFoundException
import com.pjb.todo.todobackend.exception.TodoErrorCodes
import com.pjb.todo.todobackend.model.dto.TodoDTO
import com.pjb.todo.todobackend.model.entity.todo.Todo
import com.pjb.todo.todobackend.repository.todo.TodoRepository
import com.pjb.todo.todobackend.util.asyncIO
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {

    fun findAll() = todoRepository.findAll()


    suspend fun getTodoById(id: Long) = coroutineScope {
        val todo = asyncIO{ todoRepository.findById(id) }
        todo.await() ?: run { throw NotFoundException(TodoErrorCodes.ERR_TODO_NOT_FOUND) }
    }

    suspend fun createTodo(todoDTO: TodoDTO) = coroutineScope {
        val result = asyncIO { todoRepository.save(
            Todo(
                id = todoDTO.id,
                task = todoDTO.task,
                isFinished = todoDTO.isFinished
            )
        ) }
        result.await()
    }

    suspend fun updateTodo(id: Long, payload: TodoDTO) = coroutineScope {
        val todoById = asyncIO { todoRepository.findById(id) }
        if (todoById.await() == null) throw BadRequestException(TodoErrorCodes.ERR_TODO_BAD_REQUEST)
        val updateTodo = asyncIO { createTodo(
            TodoDTO(id = id, task = payload.task, isFinished = payload.isFinished)
        ) }
        updateTodo
    }

    suspend fun deleteTodo(id: Long) = coroutineScope {
        val todoById = asyncIO { todoRepository.findById(id) }
        if (todoById.await() == null) throw BadRequestException(TodoErrorCodes.ERR_TODO_BAD_REQUEST)
        todoRepository.deleteById(id)
    }
}
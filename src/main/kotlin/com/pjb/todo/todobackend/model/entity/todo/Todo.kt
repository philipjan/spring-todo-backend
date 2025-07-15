package com.pjb.todo.todobackend.model.entity.todo

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("tbl_todo")
data class Todo(
    @Id val id: Long? = null,
    val task: String,
    @Column("is_finished") val isFinished: Boolean
)
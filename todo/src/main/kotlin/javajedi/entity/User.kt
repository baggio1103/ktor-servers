package javajedi.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val username: String,
    val password: String
)

data class Project(
    val id: Long,
    val userId: Long,
    val name: String
)

@Serializable
data class Todo(
    val id: Long,
    val projectId: Long,
    val title: String,
    val details: String,
    val assignedTo: String,
    val dueDate: String,
    val importance: String
)
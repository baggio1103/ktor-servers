package javajedi.repository

import javajedi.entity.Project
import javajedi.entity.Todo
import javajedi.entity.User

class Database {

    private val users = mutableListOf(
        User(1, "dave", "dave-password"),
        User(2, "bruce", "dave-password")
    )

    fun users() = users

    fun addUser(user: User) = users.add(user)

    fun projects() = mutableListOf(
        Project(1, 1, "New Ktor Course"),
        Project(2, 1, "Garden")
    )

    fun todos() = mutableListOf(
        Todo(
            1,
            1,
            "Start Ktor Course",
            "About time I started Ktor Course",
            "dave",
            "2024-09-01",
            "HIGH"
        ),
        Todo(
            1,
            1,
            "Start Ktor Course",
            "Add routing chapter",
            "bruce",
            "2024-09-01",
            "HIGH"
        ),
    )

}
package javajedi.repository

import javajedi.entity.Project
import javajedi.entity.Todo

class TodoRepository(private val database: Database) {

    fun getTodos(limit: Int): List<Todo> {
        return database.todos().take(limit)
    }

    fun getTodo(id: Long): Todo? {
        return database.todos().find { it.id == id }
    }

    fun getProjects(): List<Project> {
        return database.projects()
    }

}
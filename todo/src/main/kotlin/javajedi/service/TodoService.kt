package javajedi.service

import javajedi.entity.Todo
import javajedi.repository.TodoRepository

class TodoService(
    private val todoRepository: TodoRepository
) {

    suspend fun getAllTodos(limit: Int?): List<Todo>  = todoRepository.getTodos(limit ?: 10)

    suspend fun getTodo(id: Long): Todo? {
        return todoRepository.getTodo(id)
    }

}
package javajedi.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.service.TodoService

fun Route.todoRouting(todoService: TodoService) {

    route("/todos") {

        get {
            call.respond("Hell world")
        }

    }
}
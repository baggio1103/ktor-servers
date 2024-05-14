package javajedi.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.dto.RegisterUser
import javajedi.service.TodoService
import javajedi.service.UserService
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {

        val todoService by inject<TodoService>()

        val userService by inject<UserService>()

        route("/users") {

            get {
                val users = userService.getAllUsers()
                call.respond(users)
            }

            post("/register") {
                val registerUser = call.receive<RegisterUser>()
                if (userService.register(registerUser)) {
                    call.response.status(HttpStatusCode.OK)
                } else
                    call.response.status(HttpStatusCode.BadRequest)
            }
        }
        route("api") {
            todoRoutes(todoService)
            projectRoutes(todoService)
        }
    }
}

fun Route.todoRoutes(todoService: TodoService) {
    get("/todos") {
        val limit = call.request.queryParameters["id"]?.toInt() ?: 5
        val limits = todoService.getAllTodos(limit)
        call.respond(limits)
        call.respondText("Got all todos")
    }
    get("/todos/{id}") {
        call.respondText("Got to do by id: ${call.parameters["id"]}")
    }
}

fun Route.projectRoutes(todoService: TodoService) {
    route("/projects") {
        get("") {
            call.respondText("Got projects")
        }
    }
}



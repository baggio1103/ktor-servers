package javajedi.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.auth.JWTokenService
import javajedi.dto.JWTResponse
import javajedi.dto.LoginUser
import javajedi.dto.RegisterUser
import javajedi.service.TodoService
import javajedi.service.UserService
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {

        val todoService by inject<TodoService>()

        val userService by inject<UserService>()


        val jwtTokenService by inject<JWTokenService>()

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

            post("/login") {
                val loginUser = call.receive<LoginUser>()
                if (userService.login(loginUser)) {
                    val token = jwtTokenService.createToken(loginUser.login)
                    call.respond(HttpStatusCode.OK, JWTResponse(token))
                } else
                    call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
            }

        }
        authenticate {
            route("api") {
                todoRoutes(todoService)
                projectRoutes(todoService)
            }
        }
    }
}

fun Route.todoRoutes(todoService: TodoService) {
    get("/todos") {
        val limit = call.request.queryParameters["limit"]?.toInt() ?: 5
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



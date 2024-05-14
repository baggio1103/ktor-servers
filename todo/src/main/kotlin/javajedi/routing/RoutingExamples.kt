package javajedi.routing

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.routes() {

    get("/") {
        call.respondText("Hello World!")
    }

    get("/users") {
        call.respondText("Got users")
    }

    get("/users/{name}") {
        val person = call.parameters["name"]
        call.respondText("Hello, $person")
    }

    get("/books/{title?}") {
        val title = call.parameters["title"]
        call.respondText("Book by title: $title")
    }

    get("/cars/") {
        val path = call.request.path()
        call.respondText { "Received request by car path : $path" }
    }

    get("/cars/*") {
        val path = call.request.path()
        call.respondText { "Received wild card request by path: $path" }
    }

    get("/cars/{...}") {
        val path = call.request.path()
        call.respondText { "Received tail card request by path: $path" }
    }

}
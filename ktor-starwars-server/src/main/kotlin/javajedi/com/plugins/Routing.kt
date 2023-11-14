package javajedi.com.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.com.routes.customerRouting
import javajedi.com.routes.starWarsRouting

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        customerRouting()
        starWarsRouting()
    }
}

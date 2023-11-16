package javajedi.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import javajedi.routing.starWarsRouting

fun Application.configureRouting() {
    routing {
        starWarsRouting()
    }
}

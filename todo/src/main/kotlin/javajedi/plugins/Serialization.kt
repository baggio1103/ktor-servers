package javajedi.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.entity.User

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

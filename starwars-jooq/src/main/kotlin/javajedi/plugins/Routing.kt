package javajedi.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import javajedi.routing.login
import javajedi.routing.starWarsRouting

fun Application.configureRouting() {
    val environmentConfig = commandLineEnvironment(arrayOf("application.conf")).config
    routing {
        login(environmentConfig)
        authenticate {
            starWarsRouting()
        }
    }
}

package javajedi.routing

import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.service.StarWarsService
import javajedi.plugins.userPrincipal

fun Route.starWarsRouting() {

    val starWarsService by lazy { StarWarsService() }

    route("/") {
        get {
            call.respondText("Hello World!")
        }
        get("/star-wars") {
            val user = call.userPrincipal<JWTPrincipal>()
            println("User info: ${user.payload.claims}")
            call.respond(starWarsService.starWarsFilms())
        }
        get("/star-wars-ratings") {
            call.respond(starWarsService.starWarsUserRatings())
        }
    }

}
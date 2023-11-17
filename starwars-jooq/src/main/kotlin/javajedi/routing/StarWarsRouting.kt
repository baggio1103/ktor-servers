package javajedi.routing

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.StarWarsService

fun Route.starWarsRouting() {

    val starWarsService by lazy { StarWarsService() }

    route("/") {
        get {
            call.respondText("Hello World!")
        }
        get("/star-wars") {
            call.respond(starWarsService.starWarsFilms())
        }
        get("/star-wars-ratings") {
            call.respond(starWarsService.starWarsUserRatings())
        }
    }

}
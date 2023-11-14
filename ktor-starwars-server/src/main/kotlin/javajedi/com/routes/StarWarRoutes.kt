package javajedi.com.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.com.db.DatabaseFactory.dbQuery
import javajedi.com.model.StarWarsFilm
import kotlinx.serialization.Serializable

fun Route.starWarsRouting() {

    route("/star-wars") {
        get {
            val films = dbQuery {
                StarWarsFilm.all().map {
                    StarWarsFilm(it.name, it.director, it.sequelId)
                }
            }
            call.respond(films)
        }
    }

}

@Serializable
data class StarWarsFilm(val name: String, val director: String, val sequelId: Int)
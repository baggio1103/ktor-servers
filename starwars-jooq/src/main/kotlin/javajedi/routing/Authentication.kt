package javajedi.routing

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.UserService
import javajedi.data.UserLogin
import java.util.*

fun Route.login(config: ApplicationConfig) {

    val userService by lazy { UserService() }

    route("/") {

        post("/login") {
            val user = call.receive<UserLogin>()
            // Check here if user exists
            val secret = config.property("jwt.secret").getString()
            val issuer = config.property("jwt.issuer").getString()
            val audience = config.property("jwt.audience").getString()

            val token = JWT.create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("username", user.username)
                .withExpiresAt(Date(System.currentTimeMillis() + 600000))
                .sign(Algorithm.HMAC256(secret))
            call.respond(hashMapOf("token" to token))
        }

    }

}

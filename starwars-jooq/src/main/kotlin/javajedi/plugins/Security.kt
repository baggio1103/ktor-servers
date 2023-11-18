package javajedi.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import java.lang.IllegalStateException

fun Application.authentication() {
    val environmentConfig = commandLineEnvironment(arrayOf("application.conf")).config
    val secret = environmentConfig.property("jwt.secret").getString()
    val issuer = environmentConfig.property("jwt.issuer").getString()
    val audience = environmentConfig.property("jwt.audience").getString()
    val jwtRealm = environmentConfig.property("jwt.realm").getString()
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(audience)) JWTPrincipal(credential.payload) else null
            }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is invalid or expired")
            }
        }
    }

}

inline fun <reified P : Principal> ApplicationCall.userPrincipal(): P {
    return this.principal() ?: throw IllegalStateException()
}
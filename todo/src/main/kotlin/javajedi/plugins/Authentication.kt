package javajedi.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import javajedi.auth.JWTokenService
import org.koin.ktor.ext.inject

fun Application.configureAuthentication() {

    val jwTokenService by inject<JWTokenService>()

    authentication {
        jwt {
            verifier {
                jwTokenService.verify()
            }
            validate {
                jwTokenService.validate(it)
            }
            challenge { defaultScheme, realm ->
                println("Default scheme: $defaultScheme, realm: $realm")
                call.respond(HttpStatusCode.Unauthorized, "Token is invalid or has expired")
            }
        }
    }

}

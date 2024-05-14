package javajedi.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Verification
import io.ktor.server.auth.jwt.*
import java.time.Instant
import java.util.*

class JWTokenService(private val secret: String, private val issuer: String, private val audience: String) {

    fun createToken(username: String): String = jwt {
        withAudience(audience)
        withIssuer(issuer)
        withExpiresAt(Date())
        withExpiresAt(Instant.now().plusSeconds(5 * 24 * 60 * 60))
        withClaim("username", username)
        sign(Algorithm.HMAC256(secret))
    }

    fun verify() = verifier {
        withAudience(audience)
        withIssuer(issuer)
        build()
    }

    fun validate(jwtCredential: JWTCredential): JWTPrincipal? {
        return if (jwtCredential.payload.claims["username"]?.asString() != null) {
            JWTPrincipal(jwtCredential.payload)
        } else null
    }

    private fun verifier(block: Verification.() -> JWTVerifier): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(secret)).block()
    }

    private fun jwt(block: JWTCreator.Builder.() -> String): String {
        return JWT.create().block()
    }

}
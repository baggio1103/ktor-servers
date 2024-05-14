package javajedi.plugins

import io.ktor.server.*
import io.ktor.server.application.*
import javajedi.service.di.jwtModule
import javajedi.service.di.repositoryModule
import javajedi.service.di.serviceModule
import javajedi.service.di.userModule
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(
            serviceModule, repositoryModule, userModule,
            jwtModule(
                environment.config.property("jwt.audience").getString(),
                environment.config.property("jwt.issuer").getString(),
                environment.config.property("jwt.secret").getString(),
            )
        )
    }
}
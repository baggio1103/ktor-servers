package javajedi.plugins

import io.ktor.server.application.*
import javajedi.service.di.repositoryModule
import javajedi.service.di.serviceModule
import javajedi.service.di.userModule
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(serviceModule, repositoryModule, userModule)
    }
}
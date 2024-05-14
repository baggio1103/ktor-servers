package javajedi

import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import io.ktor.server.routing.IgnoreTrailingSlash
import javajedi.plugins.*

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    install(IgnoreTrailingSlash)
    configureKoin()
    configureAuthentication()
    configureMonitoring()
    configureRouting()
    configureSerialization()
}

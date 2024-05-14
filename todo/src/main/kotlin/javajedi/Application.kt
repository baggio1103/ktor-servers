package javajedi

import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import io.ktor.server.routing.IgnoreTrailingSlash
import javajedi.plugins.configureMonitoring
import javajedi.plugins.configureKoin
import javajedi.plugins.configureRouting
import javajedi.plugins.configureSerialization

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    install(IgnoreTrailingSlash)
    configureKoin()
    configureMonitoring()
    configureRouting()
    configureSerialization()
}

package javajedi.com

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import javajedi.com.db.DatabaseFactory
import javajedi.com.dbpopulation.starwars.Launch
import javajedi.com.dbpopulation.users.UserLaunch
import javajedi.com.plugins.configureRouting
import javajedi.com.plugins.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0",  module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val environmentConfig = commandLineEnvironment(arrayOf("application.conf")).config
    DatabaseFactory.init(environmentConfig)
    configureSerialization()
    configureRouting()
    Launch.init()
    UserLaunch.run()
}
package javajedi.com.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import javajedi.com.model.Customers
import javajedi.com.model.StarWarsFilms
import javajedi.com.model.UserRatings
import javajedi.com.model.Users
import kotlinx.coroutines.Dispatchers
import org.flywaydb.core.Flyway
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(config: ApplicationConfig) {
        val driverClassName = config.propertyOrNull("storage.driverClassName")?.getString() ?: "org.postgresql.Driver"
        val jdbcURL =
            config.propertyOrNull("storage.jdbcURL")?.getString() ?: "jdbc:postgresql://localhost:5432/customers"
        val username = config.propertyOrNull("storage.username")?.getString() ?: "baggio"
        val password = config.propertyOrNull("storage.password")?.getString() ?: "baggio"
        val hikari = createHikariDatasource(jdbcURL, driverClassName, username, password)
        val database = Database.connect(hikari)
        val flyway = Flyway.configure().dataSource(hikari).load()
        flyway.migrate()
        transaction(database) {
            SchemaUtils.drop(Customers, UserRatings, StarWarsFilms, Users)
            SchemaUtils.create(Customers, StarWarsFilms, Users, UserRatings)
        }
    }

    private fun createHikariDatasource(
        url: String,
        driver: String,
        dbUsername: String,
        dbPassword: String
    ): HikariDataSource {
        return HikariDataSource(HikariConfig().apply {
            driverClassName = driver
            jdbcUrl = url
            username = dbUsername
            password = dbPassword
            maximumPoolSize = 5
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        })
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T {
        return newSuspendedTransaction(Dispatchers.IO) {
            block()
        }
    }

}
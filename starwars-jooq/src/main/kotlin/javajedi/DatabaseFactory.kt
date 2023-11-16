package javajedi

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.conf.MappedSchema
import org.jooq.conf.RenderMapping
import org.jooq.conf.Settings
import org.jooq.impl.DSL

object DatabaseFactory {

    fun dsl(): DSLContext {
        val hikariDatasource = createHikariDatasource()
        val settings = Settings().withRenderMapping(
            RenderMapping().withSchemata(
                MappedSchema().withInput("public")
                    .withOutput(hikariDatasource.schema)
            )
        )
        return DSL.using(hikariDatasource, SQLDialect.POSTGRES, settings)
    }

    private fun createHikariDatasource(): HikariDataSource {
        return HikariDataSource(HikariConfig().apply {
            driverClassName = "org.postgresql.Driver"
            jdbcUrl = "jdbc:postgresql://localhost:5432/starwars"
            username = "baggio"
            password = "baggio"
            maximumPoolSize = 5
            isAutoCommit = true
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        })
    }

    suspend fun <T> dbQuery(block: () -> T): T {
        return withContext(Dispatchers.IO) {
            block()
        }
    }

}
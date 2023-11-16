package javajedi

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.SQLDialect
import org.jooq.conf.MappedSchema
import org.jooq.conf.RenderMapping
import org.jooq.conf.Settings
import org.jooq.impl.DSL

object DatabaseFactory {

    fun dsl(): DSLContext {
        val connectionFactory = ConnectionFactories.get(
            builder()
                .option(DRIVER, "postgresql")
                .option(HOST, "localhost")
                .option(PORT, 5432)
                .option(USER, "baggio")
                .option(PASSWORD, "baggio")
                .option(DATABASE, "starwars")
                .build()
        )
        return DSL.using(connectionFactory)
//        val hikariDatasource = createHikariDatasource()
//        val settings = Settings().withRenderMapping(
//            RenderMapping().withSchemata(
//                MappedSchema().withInput("public")
//                    .withOutput(hikariDatasource.schema)
//            )
//        )
//        return DSL.using(hikariDatasource, SQLDialect.POSTGRES, settings)
    }

    suspend fun <T> dbQuery(block: () -> T): T {
        return withContext(Dispatchers.IO) {
            block()
        }
    }

}
package javajedi

import com.javajedi.Tables.STARWARSFILMS
import javajedi.DatabaseFactory.dbQuery
import javajedi.DatabaseFactory.dsl
import org.jooq.DSLContext

class StarWarsService {

    private val dslContext: DSLContext by lazy { dsl() }

    suspend fun starWarsFilms(): List<StarWarsFilm> {
        return dbQuery {
            dslContext.selectFrom(STARWARSFILMS).map {
                StarWarsFilm(it[STARWARSFILMS.SEQUEL_ID], it[STARWARSFILMS.NAME], it[STARWARSFILMS.DIRECTOR])
            }
        }
    }

}
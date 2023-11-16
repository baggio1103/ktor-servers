package javajedi

import com.javajedi.public_.tables.Starwarsfilms.STARWARSFILMS
import javajedi.DatabaseFactory.dsl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import org.jooq.DSLContext
import reactor.core.publisher.Flux

class StarWarsService {

    private val dslContext: DSLContext by lazy { dsl() }

    suspend fun starWarsFilms(): Flow<StarWarsFilm> {
        val starWarsFilmFlow = Flux.from(dslContext.selectFrom(STARWARSFILMS))
            .map {
                StarWarsFilm(it[STARWARSFILMS.SEQUEL_ID], it[STARWARSFILMS.NAME], it[STARWARSFILMS.DIRECTOR])
            }.asFlow()
//        val starWarsFilmFlow = dbQuery {
//            dslContext.selectFrom(STARWARSFILMS).asFlow().map {
//                StarWarsFilm(it[STARWARSFILMS.SEQUEL_ID], it[STARWARSFILMS.NAME], it[STARWARSFILMS.DIRECTOR])
//            }
//        }
        starWarsFilmFlow.collect {
            println(it)
        }
        return flow {
            emit(StarWarsFilm(1, "baggio", "baggio"),)
            delay(1000)
            emit(StarWarsFilm(1, "dave", "dave"))
            delay(1000)
            emit(StarWarsFilm(1, "bruce", "bruce"))
            delay(1000)
            emit(StarWarsFilm(1, "wayne", "wayne"))
        }
    }

}
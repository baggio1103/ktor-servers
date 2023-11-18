package javajedi

import com.javajedi.Tables.*
import javajedi.DatabaseFactory.dbQuery
import javajedi.DatabaseFactory.dsl
import javajedi.data.StarWarsFilm
import javajedi.data.StarWarsUserRatings
import javajedi.data.UserRating
import org.jooq.DSLContext
import org.jooq.Records
import org.jooq.impl.DSL.multiset

class StarWarsService {

    private val dslContext: DSLContext by lazy { dsl() }

    suspend fun starWarsFilms(): List<StarWarsFilm> {
        return dbQuery {
            dslContext.selectFrom(STARWARSFILMS).map {
                StarWarsFilm(it[STARWARSFILMS.SEQUEL_ID], it[STARWARSFILMS.NAME], it[STARWARSFILMS.DIRECTOR])
            }
        }
    }

    suspend fun starWarsUserRatings(): List<StarWarsUserRatings> {
        return dbQuery {
            dslContext.select(
                STARWARSFILMS.NAME,
                STARWARSFILMS.DIRECTOR,
                multiset(
                    dslContext.select(USERS.NAME, USER_RATINGS.VALUE).from(
                        USER_RATINGS.innerJoin(USERS).on(USER_RATINGS.USER_ID.eq(USERS.ID))
                    ).where(USER_RATINGS.FILM_ID.eq(STARWARSFILMS.ID))
                ).`as`("userRatings").convertFrom { row -> row.map { UserRating(it[USERS.NAME], it[USER_RATINGS.VALUE]) } }
            )
                .from(STARWARSFILMS)
                .fetch(Records.mapping(::StarWarsUserRatings))
        }
    }

}
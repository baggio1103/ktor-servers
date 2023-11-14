package javajedi.com.dbpopulation.users

import javajedi.com.db.DatabaseFactory.dbQuery
import javajedi.com.model.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.dao.load
import org.jetbrains.exposed.dao.with
import org.jetbrains.exposed.sql.selectAll

object UserLaunch {

    fun run() {
        runBlocking {
            create()
            read()
            rateFilm()
            filmRatings()
            specificFilmRatings()
            joinUserRatings()
            wrapExpression()
            filmsWithNestedUsers()
        }
    }

    suspend fun create() {
        dbQuery {
            User.new {
                name = "baggio"
            }
            User.new {
                name = "bruce"
            }
            User.new {
                name = "wayne"
            }
            User.new {
                name = "dave"
            }
        }
    }

    suspend fun read() {
        dbQuery {
            println("Reading users:")
            val users = User.all().asSequence()
            users.forEach {
                println(it)
            }
            println("Finished reading users\n")
        }
    }

    suspend fun rateFilm() {
        dbQuery {
            println("User rating...")
            val baggio = User.find { Users.name eq "baggio" }.first()
            val dave = User.find { Users.name eq "bruce" }.first()
            val starWarsFilm = StarWarsFilm.find { StarWarsFilms.name eq "The last Jedi" }.first()
            println("Users: {$baggio and $dave rating film = $starWarsFilm}")
            UserRating.new {
                value = 5
                film = starWarsFilm
                user = baggio
            }
            UserRating.new {
                value = 4
                film = starWarsFilm
                user = dave
            }
            println("User rating finished...")
        }
    }

    suspend fun filmRatings() {
        dbQuery {
            println("Viewing user ratings")
            val userRatings = UserRating.all().with(UserRating::user, UserRating::film)
            userRatings.forEach { println(it) }
            println("Finished viewing film ratings")
        }
    }

    //Eager loading of One-to-Many relationship
    // Generates 3 queries
    suspend fun specificFilmRatings() {
        dbQuery {
            println("Viewing the film 'The Last Jedi'  with its ratings")
            val starWarsFilm = StarWarsFilm.find { StarWarsFilms.name eq "The last Jedi" }
                .single()
                .load(StarWarsFilm::ratings, UserRating::user)
            println("Film: $starWarsFilm and ratings: ${starWarsFilm.ratings.toList()}")
            println("Finished viewing 'The Last Jedi' ratings...")
        }
    }

    // Inner join
    suspend fun joinUserRatings() {
        dbQuery {
            println("Viewing full info about user rating... ")
            (UserRatings innerJoin StarWarsFilms)
                .innerJoin(Users)
                .slice(UserRatings.value, StarWarsFilms.name, StarWarsFilms.director, StarWarsFilms.sequelId, Users.name)
                .selectAll()
                .forEach {
                    println("UserRating[Film: ${it[StarWarsFilms.name]}, director: ${it[StarWarsFilms.director]}, sequelId: ${it[StarWarsFilms.sequelId]}, userName: ${it[Users.name]}, value:${it[UserRatings.value]}]")
                }
            println("Finished viewing full info about user rating")
        }
    }

    suspend fun wrapExpression() {
        dbQuery {
            println("Viewing users with ratings")
            val usersWithRatings = User.wrapRows(
                (UserRatings innerJoin StarWarsFilms)
                    .innerJoin(Users)
                    .slice(Users.columns)
                    .selectAll()
            )
            println("Users with views: ${usersWithRatings.toList()}")
            println("Finished viewing users with ratings")
        }
    }

    suspend fun filmsWithNestedUsers() {
        dbQuery {
            println("Start...")
            val result = (StarWarsFilms innerJoin UserRatings)
                .innerJoin(Users)
                .slice(StarWarsFilms.name, Users.name)
                .selectAll()
//                .forEach {
//                    println("Film name: ${it[StarWarsFilms.name]}, username: ${it[Users.name]}")
//                }
            println("End...")
        }
    }

}

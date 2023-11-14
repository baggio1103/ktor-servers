package javajedi.com.dbpopulation.starwars

import javajedi.com.db.DatabaseFactory.dbQuery
import javajedi.com.model.StarWarsFilm
import javajedi.com.model.StarWarsFilms
import kotlinx.coroutines.runBlocking

object Launch {

    fun init() {
        runBlocking {
            create()
            read()
            readById()
            readByProperty()
            update()
            delete()
        }
    }

    suspend fun create() {
        dbQuery {
            StarWarsFilm.new {
                sequelId = 8
                name = "The last Jedi"
                director = "Rian Johnson"
            }
            StarWarsFilm.new {
                sequelId = 1
                name = "The phantom menace"
                director = "Dave Filoni"
            }
            StarWarsFilm.new {
                sequelId = 2
                name = "Attack of clones"
                director = "Dave Filoni"
            }
            StarWarsFilm.new {
                sequelId = 3
                name = "Revenge of the Sith"
                director = "Dave Filoni"
            }
            StarWarsFilm.new {
                sequelId = 4
                name = "A new hope"
                director = "George Lukas"
            }
        }
    }

    suspend fun read() {
        dbQuery {
            val films = StarWarsFilm.all()
            films.forEach { println(it) }
        }
    }

    suspend fun readById() {
        dbQuery {
            val starWarsFilm = StarWarsFilm.findById(1)
            println("Film by id: $starWarsFilm")
        }
    }

    suspend fun readByProperty() {
        dbQuery {
            val phantomMenace = StarWarsFilm.find { StarWarsFilms.sequelId eq 1 }.single()
            println("Result: $phantomMenace")
            println("Name: ${phantomMenace.name}")
        }
    }

    suspend fun update() {
        dbQuery {
            val starWarsFilm = StarWarsFilm.find { StarWarsFilms.sequelId eq 3 }.single()
            println("Before: $starWarsFilm")
            starWarsFilm.director = "George Lukas"
            val updated = StarWarsFilm.find { StarWarsFilms.sequelId eq 3 }.single()
            println("After update: $updated")
        }
    }

    suspend fun delete() {
        dbQuery {
            println("Deleting")
            val starWarsFilms = StarWarsFilm.find { StarWarsFilms.sequelId eq 3 }.single()
            println("StarWars Film: $starWarsFilms")
            starWarsFilms.delete()
            val starWarsFilm = StarWarsFilm.find { StarWarsFilms.sequelId eq 3 }.singleOrNull()
            println("After deletion: $starWarsFilm")
        }
    }

}
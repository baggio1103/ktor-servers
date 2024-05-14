package javajedi.repository

import javajedi.entity.User

class UserRepository(
    private val database: Database
) {

    fun getUsers() = database.users()

    fun insertUser(user: User): Boolean {
        return database.addUser(user)
    }

    fun getUserByName(username: String): User? {
        return database.users().find { it.username == username }
    }

}
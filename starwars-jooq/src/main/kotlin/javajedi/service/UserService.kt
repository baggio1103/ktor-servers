package javajedi.service

import com.javajedi.tables.Users.USERS
import javajedi.DatabaseFactory.dsl
import javajedi.Hashing.matches
import javajedi.data.User

class UserService {

    private val dslContext by lazy { dsl() }

    fun existsByNameAndPassword(username: String, password: String): Boolean {
        val user = dslContext.selectFrom(USERS).where(USERS.NAME.eq(username))
                .fetchSingle()
                .map { User(it[USERS.NAME], it[USERS.PASSWORD]) }
        if (matches(hashed = user.password, plainText = password)) {
            return true
        }
        return false
    }

}
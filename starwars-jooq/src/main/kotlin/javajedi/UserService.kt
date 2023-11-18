package javajedi

import javajedi.DatabaseFactory.dsl

class UserService {

    val dslContext by lazy { dsl() }

//    fun existsByName(username: String, password: String) {
//        dslContext.selectFrom(USERS).where(USERS.NAME.eq(username)).map { User(it[USERS.NAME], it[USERS.PASSWORD]) }
//    }

}
package javajedi

import org.mindrot.jbcrypt.BCrypt

object Hashing {

    private val salt: String by lazy { BCrypt.gensalt() }

    fun hash(password: String): String {
        return BCrypt.hashpw(password, salt)
    }

    fun matches(hashed: String, plainText: String): Boolean {
        return BCrypt.checkpw(plainText, hashed)
    }

}
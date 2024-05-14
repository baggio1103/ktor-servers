package javajedi.service

import javajedi.dto.LoginUser
import javajedi.dto.RegisterUser
import javajedi.entity.User
import javajedi.repository.UserRepository
import kotlin.random.Random

class UserService(
    private val userRepository: UserRepository
) {

    suspend fun register(user: RegisterUser): Boolean {
        if (userRepository.getUserByName(user.login) != null) return false
        return userRepository.insertUser(User(Random.nextLong(50, 10000), user.login, user.password))
    }

    suspend fun login(user: LoginUser): Boolean {
        return userRepository.getUserByName(user.login) != null
    }

    suspend fun getAllUsers() = userRepository.getUsers()

}
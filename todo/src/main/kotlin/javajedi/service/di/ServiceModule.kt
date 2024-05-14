package javajedi.service.di

import javajedi.repository.Database
import javajedi.repository.TodoRepository
import javajedi.repository.UserRepository
import javajedi.service.TodoService
import javajedi.service.UserService
import org.koin.dsl.module

val repositoryModule = module {
    factory { Database() }
    single<TodoRepository> { TodoRepository(database = get()) }
    single<UserRepository> { UserRepository(database = get()) }
}

val serviceModule = module {
    single<TodoService> { TodoService(todoRepository = get()) }
}

val userModule = module {
    single<UserService> { UserService(userRepository = get()) }
}
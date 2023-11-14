package javajedi.com.di

import javajedi.com.repository.DAOFacadeImpl
import javajedi.com.service.CustomerService
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val kodein = DI {
    bindProvider { DAOFacadeImpl() }
    bindProvider { CustomerService(instance()) }
}

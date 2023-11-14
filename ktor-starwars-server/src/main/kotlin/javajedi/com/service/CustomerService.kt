package javajedi.com.service

import javajedi.com.model.Customer
import javajedi.com.repository.DAOFacade

class CustomerService(
    private val daoFacade: DAOFacade
) {

    suspend fun findAllCustomers() = daoFacade.allCustomers()

    suspend fun findById(id: Int): Customer? {
       return daoFacade.findById(id)
    }

    suspend fun addNewCustomer(name: String) {
       daoFacade.addCustomer(name)
    }

    suspend fun deleteById(id: Int) {
        daoFacade.deleteCustomer(id)
    }

}
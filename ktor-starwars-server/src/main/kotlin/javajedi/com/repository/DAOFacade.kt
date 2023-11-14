package javajedi.com.repository

import javajedi.com.model.Customer

interface DAOFacade {

    suspend fun allCustomers(): List<Customer>

    suspend fun findById(id: Int): Customer?

    suspend fun addCustomer(name: String)

    suspend fun deleteCustomer(id: Int)

}
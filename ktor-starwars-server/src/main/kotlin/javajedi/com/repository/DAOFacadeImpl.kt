package javajedi.com.repository

import javajedi.com.db.DatabaseFactory.dbQuery
import javajedi.com.model.Customer
import javajedi.com.model.Customers
import javajedi.com.model.Customers.name
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToCustomer(row: ResultRow): Customer {
        return Customer(row[Customers.id], row[name])
    }

    override suspend fun allCustomers(): List<Customer> {
        return dbQuery {
            Customers.selectAll().map(::resultRowToCustomer)
        }
    }

    override suspend fun findById(id: Int): Customer? {
        return dbQuery {
            Customers.select { Customers.id eq id }.map { resultRowToCustomer(it) }.singleOrNull()
        }
    }

    override suspend fun addCustomer(name: String) {
        val insert = dbQuery {
            Customers.insert {
                it[Customers.name] = name
            }
        }
        val value = insert.resultedValues?.singleOrNull()?.let {
            resultRowToCustomer(it)
        }
        println("Inserted value: $value")
    }

    override suspend fun deleteCustomer(id: Int) {
        dbQuery { Customers.deleteWhere { Customers.id eq id } }
    }

}
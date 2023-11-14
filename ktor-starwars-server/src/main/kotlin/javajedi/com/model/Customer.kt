package javajedi.com.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Customer(val id: Int, val name: String)


object Customers: Table() {

    val id = integer("id").autoIncrement()

    val name = varchar("name", 128)

    override val primaryKey = PrimaryKey(id, name = "pk_customer_id")

}
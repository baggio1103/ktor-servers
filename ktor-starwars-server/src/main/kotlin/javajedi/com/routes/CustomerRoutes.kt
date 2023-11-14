package javajedi.com.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javajedi.com.di.kodein
import javajedi.com.model.HttpResponse
import javajedi.com.request.CustomerRequest
import javajedi.com.service.CustomerService
import org.kodein.di.instance
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Route.customerRouting() {

    route("/customers") {

        val customerService by kodein.instance<CustomerService>()

        val log: Logger = LoggerFactory.getLogger("Customer")

        get {
            call.respond(customerService.findAllCustomers())
        }

        get("/{id?}") {
            val id = call.parameters["id"]
                ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
            val customer = customerService.findById(id.toInt())
                ?: return@get call.respondText("Customer not found with id: $id", status = HttpStatusCode.BadRequest)
            log.info("Customer: $customer")
            call.respond(customer)
        }

        post {
            val customer = call.receive<CustomerRequest>()
            log.info("Customer: $customer")
            customerService.addNewCustomer(customer.name)
            call.respond(HttpStatusCode.Created, HttpResponse("Customer uploaded successfully"))
        }

        delete("{id?}") {
            val id = call.parameters["id"]
                ?: return@delete call.respond(HttpStatusCode.BadRequest, HttpResponse("Missing id"))
            customerService.deleteById(id.toInt())
            call.respond(HttpStatusCode.Accepted, HttpResponse("Customer deleted"))
        }

    }

}
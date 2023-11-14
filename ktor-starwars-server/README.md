# StarWars Ktor Server

---
## Ktor server example that showcases the following functionality:

- Request handling:
    
The Kotlin's powerful DSL allows to define the endpoints 
so elegantly and concisely.

The following snippet shows how requests are handled

GET

    route("/star-wars") {
        get {
            val films = dbQuery {
                StarWarsFilm.all().map {
                    StarWarsFilm(it.name, it.director, it.sequelId)
                }
            }
            call.respond(films)
        }
    }

POST
To receive the payload of request, 
we call.receive<CustomerRequest>() -> it returns the payload of the request

    post {
        val customer = call.receive<CustomerRequest>()
        log.info("Customer: $customer")
        customerService.addNewCustomer(customer.name)
        call.respond(HttpStatusCode.Created, HttpResponse("Customer uploaded successfully"))
    }


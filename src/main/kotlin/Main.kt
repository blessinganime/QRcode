import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun main() {
    println("🚀 Server is starting...")

    embeddedServer(Netty, port = 8080) {
        routing {
            get("/qr") {
                val text = call.request.queryParameters["text"]

                if (text.isNullOrBlank()) {
                    call.respond(HttpStatusCode.BadRequest, "Missing 'text'")
                    return@get
                }

                val stream = generateQRCodeStream(text)

                call.response.header(HttpHeaders.ContentType, "image/png")
                call.respondBytes(stream.toByteArray())
            }
        }
    }.start(wait = true)
}

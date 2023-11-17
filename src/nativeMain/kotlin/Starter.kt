import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun startKtor() {
    println("starting ktor")
    embeddedServer(CIO, port = 8000) {
        routing {
            get ("/") {
                call.respondText("Hello, world Native !\n${collatzLoop(10_000_000)}")
            }
        }
    }.start(wait = true)
}

fun collatzLoop(to: Long): Long {
    var sum = 0L;
    for (i in 1 .. to)
        sum += collatz(i)
    return sum
}

fun collatz(n: Long): Long {
    var h = n;
    var steps = 0L;
    while (h > 1) {
        if (h % 2 == 0L) h = h / 2
        else h = 3 * h + 1
        steps++
    }
    return steps
}

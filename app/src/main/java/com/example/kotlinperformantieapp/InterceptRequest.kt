import android.annotation.SuppressLint
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.KoinApplication.Companion.logger
import java.io.IOException
import java.lang.String
import java.util.logging.Logger

internal class LoggingInterceptor : Interceptor {

    private val log: Logger = Logger.getGlobal()

    @SuppressLint("DefaultLocale")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()
        println("Starttijd C $t1")

        log.info(String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()))

        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        println("Eindtijd C $t2")

        log.info(String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6, response.headers()))

        return response
    }
}
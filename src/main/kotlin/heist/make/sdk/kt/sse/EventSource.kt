package heist.make.sdk.kt.sse

import heist.make.sdk.kt.event.EventTarget
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.net.http.HttpClient
import java.net.http.HttpHeaders
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * The EventSource class connects to a Sever and receives incoming events.
 *
 * @constructor connects the URL and reads the incoming SSE messages using a BufferedReader.
 *
 * @property ReadyState enum class that indicates the status of the connection.
 */
class EventSource : EventTarget {

    private var content = ""
    private var connection: HttpURLConnection? = null;
    private val headers: Map<String, String>? = null
    var readyState = ReadyState.CLOSED

    public enum class ReadyState {
        CONNECTING, OPEN, CLOSED
    }

    constructor( uri: String ) {

        var bffr : StringBuffer = StringBuffer()
        readyState = ReadyState.CONNECTING
        connection = URL(uri).openConnection() as HttpURLConnection
        connection!!.inputStream.bufferedReader().use { reader ->

            readyState = ReadyState.OPEN

            var line = ""
            var finished = false
            while ( !finished ) {

                if ( !reader.ready() ) {
                    finished = true
                    reader.close()
                    break
                }

                line = reader.readLine()
                reader.read()
                bffr.append( line.plus( "\n" ) )
            }

        }
        content = bffr.toString()
    }

    /**
     * closes the connection to the server.
     */
    fun close() {
        connection?.disconnect()
        readyState = ReadyState.CLOSED
    }

    fun getContent(): String? {
        return content
    }

    fun getConnection(): HttpURLConnection? {
        return connection
    }

    fun getHeaders(): Map<String, String>? {
        return headers
    }

}





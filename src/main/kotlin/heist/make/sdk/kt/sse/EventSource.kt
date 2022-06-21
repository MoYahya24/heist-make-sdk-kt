package heist.make.sdk.kt.sse

import heist.make.sdk.kt.event.EventTarget
import heist.make.sdk.kt.event.MessageEvent
import heist.make.sdk.kt.sse.EventSource.ReadyState
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

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
    private var headers: Map<String, String>? = null

    var readyState = ReadyState.CLOSED

    public enum class ReadyState {
        CONNECTING, OPEN, CLOSED
    }

    constructor( uri: String ) {

        coroutineScope {
            launch {
                connect( uri )
            }
        }


    }

     suspend fun connect( uri : String ) {

        readyState = ReadyState.CONNECTING

        connection = URL( uri ).openConnection() as HttpURLConnection
        connection!!.setRequestProperty( "Accept", "text/event-stream" )
        connection!!.inputStream.bufferedReader().use { handleInputStream( it ) }
         
    }

    /**
     * Handles the input stream reader wheneven a message is sent.
     *
     * The expected Server-Sent Event format is as follows.
     * name: [Optional]
     * data: []
     * empty space (indicates the end of the Server-Sent Event)
     *
     */
    fun handleInputStream( reader : BufferedReader ) {

        readyState = ReadyState.OPEN

        var type : String = "message";
        var data : String = "";

        while ( readyState == ReadyState.OPEN ) {

            val line = reader.readLine() // Blocking function. Read stream until \n is found

            when {
                line.startsWith("event: " ) -> { // get event name
                    type = line.substring( 6 ).trim();
                }
                line.startsWith("data: " ) -> { // get data
                    data = line.substring( 5 ).trim();
                }
                line.isEmpty() -> { // empty line, finished block. Emit the event
                    this.dispatchEvent( MessageEvent( type, this, data ) )
                }
            }

        }

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





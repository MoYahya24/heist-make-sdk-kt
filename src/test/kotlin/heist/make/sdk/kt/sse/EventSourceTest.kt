package heist.make.sdk.kt.sse

import heist.make.sdk.kt.event.Event
import heist.make.sdk.kt.event.MessageEvent
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class EventSourceTest {

    private fun handleMessage( e: Event ) {

        if ( e is MessageEvent ) {
            System.out.println( "name: " + e.getType() )
            System.out.println( "data: " + e.getData() )
        }

    }

    @Test
    fun testEventSource() {

        var ev = EventSource( "http://localhost:8080/event-stream" )
        ev.addEventListener( "message", ::handleMessage )

    }

}
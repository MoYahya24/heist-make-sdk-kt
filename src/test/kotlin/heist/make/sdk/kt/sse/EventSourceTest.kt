package heist.make.sdk.kt.sse

import heist.make.sdk.kt.event.Event
import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class EventSourceTest {

    private fun handleMessage( e: Event ) {

        System.out.println( ( e as MessageEvent ).data )
    }

    private fun handleMessage2( e: Event ) {
        Assertions.assertEquals(
            "MessageEvent(\n" +
                    "data= \n" +
                    "data: Yahya!\n" +
                    "data: this is data\n" +
                    "data: I am a message\n" +
                    ", type= message)\n", e.toString()
        )
    }

    @Test
    fun testEventSource() {

        var ev = EventSource( "http://localhost:8080/stream" )
        ev.addEventListener( "message", ::handleMessage )
        ev.addEventListener( "message", ::handleMessage2 )
        ev.dispatchEvent( MessageEvent( ev, ev.bffr.toString() ) )

    }

}
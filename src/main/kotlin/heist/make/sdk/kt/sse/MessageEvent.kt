package heist.make.sdk.kt.sse

import heist.make.sdk.kt.event.Event
import heist.make.sdk.kt.event.EventTarget

class MessageEvent : Event {

    var data : String? = null

    constructor( target : EventTarget, data : String ) : super( "message", target ) {
        this.data = data
    }

    override fun toString() : String {
        return "MessageEvent(\ndata= \n$data, type= $type)\n"
    }


}
package heist.make.sdk.kt.event

import heist.make.sdk.kt.event.Event
import heist.make.sdk.kt.event.EventTarget
import kotlinx.serialization.Serializable


/**
 * The MessageEvent class is a special type of Event.
 *
 * @property type is set by default to "message".
 *
 * @property data is a String where the text message sent by the server is stored.
 */

@Serializable
class MessageEvent : Event {

    private var data: String? = null

    constructor( type : String, target : EventTarget, data : String )  : super( type, target ) {
        this.data = data
    }

    override fun toString(): String {
        return "MessageEvent(\ndata = \n$data, type = Message)\n"
    }

    fun getData(): String? {
        return data
    }


}
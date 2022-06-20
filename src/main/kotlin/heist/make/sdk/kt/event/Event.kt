package heist.make.sdk.kt.event

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


/**
 * The event class is an implementation of events sent by the server.
 *
 * @property type is a string repressnting the type of the Event.
 *
 * @property target an instance of the class EventTarget.
 */
open class Event {

    private var type: String? = null


    private var target: EventTarget? = null

    constructor(type: String, target: EventTarget) {

        this.type = type
        this.target = target

    }

    override fun toString(): String {
        return "Event()"
    }

    fun getType() : String? {
        return type
    }

    fun getTarget() : EventTarget? {
        return target
    }


}
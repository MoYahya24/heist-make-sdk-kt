package heist.make.sdk.kt.event

open class Event {

    var type : String? = null
    var target : EventTarget? = null

    constructor( type : String , target : EventTarget ) {

        this.type = type
        this.target = target

    }

    override fun toString() : String {
        return "Event()"
    }


}
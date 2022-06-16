package heist.make.sdk.kt.event

open class EventTarget {

    val listeners: MutableMap<String, MutableList<( ( e: Event ) -> Unit )>>? = HashMap();

    /**
     * Adds an event listener
     */
    fun addEventListener( type: String, listener: ( e: Event ) -> Unit ) {

        if ( !listeners?.containsKey( type )!! ) {
            listeners!!.put( type, mutableListOf( listener ) );
        } else {
            listeners!!.get( type )!!.add( listener );

        }

    }

    /**
     * Removes an event listener
     */
    fun removeEventListener( type: String, listener: ( e: Event ) -> Unit ) {

        listeners?.forEach { entry ->
            entry.value.remove( listener )

        }

    }

    /**
     * Dispatches an event
     */
    fun dispatchEvent( e: Event ) {

        listeners?.get( e.type )?.forEach {
            it( e )
        }

    }

}
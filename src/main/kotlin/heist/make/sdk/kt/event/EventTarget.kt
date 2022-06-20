package heist.make.sdk.kt.event


/**
 * The EventTarget class impplements methods that add and remove event listeners to type-specific events.
 */

open class EventTarget {

    private val listeners: MutableMap<String, MutableList<( ( e: Event ) -> Unit )>>? = HashMap();

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

        listeners?.get( e.getType() )?.forEach {
            it( e )
        }

    }

}
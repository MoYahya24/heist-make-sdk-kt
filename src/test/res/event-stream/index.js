const app=require('express')(); 

app.get('/', (req, res)=> res.send('hello!'));

app.get( '/event-stream', ( req, res)=> {

    res.setHeader('Content-Type', 'text/event-stream' );
    res.status( 200 )

    var i = 0;

    setInterval( () => {
        send(  res,'message', `{ "i": ${i++} }` )
    }, 1000 );

} )

/**
 * Sends a message with some data
 * @param type
 * @param data
 */
function send( res, type, data ) {

    res.write( `name: ${type} \n` )
    res.write( `data: ${data} \n` )
    res.write( '\n' )

}

app.listen(8080 )
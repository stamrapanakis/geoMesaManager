/**
 * 
 */
package gr.atc.polivisu.geomesa.exception;

/**
 *
 * @author SRapanakis
 */
public class SubmitCommandException extends Exception {

    public SubmitCommandException() {
    }

    //Constructor that accepts a message
    public SubmitCommandException(String message) {
        super(message);
    }
}

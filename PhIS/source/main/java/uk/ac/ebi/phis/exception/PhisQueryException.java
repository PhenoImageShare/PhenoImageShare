/**
 * @author tudose
 */
package uk.ac.ebi.phis.exception;


/**
 * @author tudose
 *
 */
public class PhisQueryException  extends Exception{
	
	private static final long serialVersionUID = -2144650449502808787L;
	
	public static final String START_END_POS = "Start and end position are meant to be used as an interval. Please provide both or use \"position\" instead.";

	public PhisQueryException(String message){
		super(message);
	}
	
}

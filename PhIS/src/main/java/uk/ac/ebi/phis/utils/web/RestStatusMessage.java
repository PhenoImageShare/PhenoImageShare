/**
 * @author tudose
 */
package uk.ac.ebi.phis.utils.web;

import org.json.JSONObject;


/**
 * @author tudose
 *
 */
public class RestStatusMessage {

	
	public static JSONObject getSuccessJson(){
		
		JSONObject obj = new JSONObject();
		obj.put("outcome", "SUCCESS");
		
		return obj;
		
	}
	
	public static JSONObject getFailJson(){
		
		JSONObject obj = new JSONObject();
		obj.put("outcome", "FAIL");
		
		return obj;
		
	}
}

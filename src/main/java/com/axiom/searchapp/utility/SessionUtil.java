package com.axiom.searchapp.utility;


import javax.servlet.http.HttpSession;

public class SessionUtil {

	private SessionUtil() {

	}

	/**
	 * Get value from the session attribute
	 * 
	 * @param request
	 * @param key
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public static <T> T getValue(HttpSession session, String key) {
		Object valueToReturn = session.getAttribute(key);
		if (valueToReturn != null) {
			return (T) valueToReturn;
		}

		return null;
	}


	/**
	 * Function to store information to the session
	 * 
	 * @param request
	 * @param key
	 * @param val
	 */
	public static void setValue(HttpSession session, String key, Object val) {
		session.setAttribute(key, val);
	}
}


package cryptho;

/**
 * SimplePasswordEncoder provides one method of encoding passwords based on
 * hashCode() method of String class
 * 
 * @author Viktor
 *
 */
public class SimplePasswordEncoder {
	private static final String secretKey = "very_secret_key";

	public static String doEncoding(String password) {
		String result = String.valueOf(password.concat(secretKey).hashCode());
		return result;
	}

}

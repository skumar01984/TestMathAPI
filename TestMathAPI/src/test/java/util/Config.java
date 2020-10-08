package util;

public abstract class Config {

	public static final String postURL = "http://api.mathjs.org/v4/";
	public static final String postJsonExpressionData = "{"
			+ "\"expr\":" 
			+	" ["
			+ "\"a = 1.2 * (2 + 4.5)\","
			+ "\"a / 2\","
			+ "\"5.08 cm in inch\","
			+ "\"sin(45 deg) ^ 2\","
			+ "\"9 / 3 + 2i\","
			+ "\"b = [-1, 2; 3, 1]\","
			+ "\"det(b)\""
			+ "],"
			+ "\"precision\": 14"
			+ "}";
    
	public static final String postJsonExpressionData2 = "{"
		+ "\"expr\":" 
		+	" ["
		+ "\"a = 1.2 * (2 + 4.5)\","
		+ "\"a / 2\""
		+ "],"
		+ "\"precision\": 14"
		+ "}";


	}

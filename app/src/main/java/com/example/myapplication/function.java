public class function
{
	public static double square (double x) {
	    return (x * x);
	}
	
	public static float func (float x, float y) {
	    int S;
	    if (square(x - 237.5) + square(y + 237.5) < square(237.5)) {
	        if (x > 237.5) {
	            if (y > 1.37638182 * x - 564.3907069) {
	                S = 19;
	            }
	            else if (y > 0.32492 * x - 314.6685165) {
	                S = 23;
	            }
	            else if (y > -0.32492 * x - 160.331483) {
	                S = 7;
	            }
	            else if (y > -1.37638192 * x + 89.3907066644) {
	                S = 3;
	            }
	            else {
	                S = 13;
	            }
	        }
	        else {
	            if (y > -1.37638192 * x + 89.3907066644) {
	                S = 5;
	            }
	            else if (y > -0.32492 * x - 160.331483) {
	                S = 2;
	            }
	            else if (y > 0.32492 * x - 314.6685165) {
	                S = 11;
	            }
	            else if (y > 1.37638182 * x - 564.3907069) {
	                S = 29;
	            }
	            else {
	                S = 17;
	            }
	        }
	    }
	    //res = (x + y) * 0.36 * S * Int;
	    return 0;
	}
}

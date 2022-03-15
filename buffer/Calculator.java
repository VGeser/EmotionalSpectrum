public static int[] getScreenSize(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        } else {
            displayMetrics = context.getResources().getDisplayMetrics();
        }
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }


public class Calculator
{	
	public static double calculator (float x, float y) {
	    int S = 0, Int = 0;
	    int R = getScreenSize() / 2;
	    double ID = 0;

	    if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < Math.pow((R), 2)) {

	        if (x > R) {

	            if (y > (R / 172.55385046) * x - (R / 0.4208077)) {
	                S = 19;
	            }
	            else if (y > (R / 730.949156715) * x - (R / 0.754762512)) {
	                S = 23;
	            }
	            else if (y > -(R / 730.949156715) * x - (R / 1.4813060)) {
	                S = 7;
	            }
	            else if (y > -(R / 172.55385046) * x + (R / 2.65687574)) {
	                S = 3;
	            }
	            else {
	                S = 13;
	            }
	        }

	        else {
	            if (y > -(R / 172.55385046) * x + (R / 2.65687574)) {
	                S = 5;
	            }
	            else if (y > -(R / 730.949156715) * x - (R / 1.4813060)) {
	                S = 2;
	            }
	            else if (y > (R / 730.949156715) * x - (R / 0.754762512)) {
	                S = 11;
	            }
	            else if (y > (R / 172.55385046) * x - (R / 0.4208077)) {
	                S = 29;
	            }
	            else {
	                S = 17;
	            }
	        }

	        if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (Math.pow((R), 2)) / 10) {
	        	Int = 10;
	        }
	        else if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (2 * Math.pow((R), 2)) / 10) {
	        	Int = 9;
	        }
	        else if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (3 * Math.pow((R), 2)) / 10) {
	        	Int = 8;
	        }
	        else if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (4 * Math.pow((R), 2)) / 10) {
	        	Int = 7;
	        }
	        else if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (5 * Math.pow((R), 2)) / 10) {
	        	Int = 6;
	        }
	        else if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (6 * Math.pow((R), 2)) / 10) {
	        	Int = 5;
	        }
	        else if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (7 * Math.pow((R), 2)) / 10) {
	        	Int = 4;
	        }
	        else if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (8 * Math.pow((R), 2)) / 10) {
	        	Int = 3;
	        }
	        else if (Math.pow((x - R), 2) + Math.pow((y + R), 2) < (9 * Math.pow((R), 2)) / 10) {
	        	Int = 2;
	        }
	        else {
	        	Int = 1;
	        }
	    }
	    ID = (x + y) * 0.36 * S * Int;
	    return ID;
	}
}

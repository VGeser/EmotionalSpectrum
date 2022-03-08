public static int[] getScreenSize(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        } else {
            displayMetrics = context.getResources().getDisplayMetrics();
        }
        return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
    }


public class Function
{	
	public static float func (float x, float y) {
	    int S;
	    int R = getScreenSize() / 2;

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
	    }
	    //res = (x + y) * 0.36 * S * Int;
	    return 0;
	}
}

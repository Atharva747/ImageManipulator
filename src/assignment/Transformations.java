package assignment;
/**
 *
 * CS314H Programming Assignment 1 - Java image processing
 *
 * Included is the Invert effect from the assignment.  Use this as an
 * example when writing the rest of your transformations.  For
 * convenience, you should place all of your transformations in this file.
 *
 * You can compile everything that is needed with
 * javac -d bin src/assignment/*.java
 *
 * You can run the program with
 * java -cp bin assignment.JIP
 *
 * Please note that the above commands assume that you are in the prog1
 * directory.
 */

import java.util.ArrayList;

class Invert extends ImageEffect {
    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[y][x] = ~pixels[y][x];
            }
        }
        return pixels;
    }
}

class Dummy extends ImageEffect {

    public Dummy() {
        super();
        params = new ArrayList<ImageEffectParam>();
        params.add(new ImageEffectParam("ParamName",
                                           "Description of param.",
                                           10, 0, 1000));
    }

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        // Use params here.
        return pixels;
    }
}


class NoRed extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y]=makePixel(0, getGreen(pixels[x][y]), getBlue(pixels[x][y]));
            }
        }
        return pixels;
    }
}


class NoGreen extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y]=makePixel(getRed(pixels[x][y]), 0, getBlue(pixels[x][y]));
            }
        }
        return pixels;
    }
}


class NoBlue extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y]=makePixel(getRed(pixels[x][y]), getGreen(pixels[x][y]), 0);
            }
        }
        return pixels;
    }
}


class RedOnly extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y]=makePixel(getRed(pixels[x][y]), 0, 0);
            }
        }
        return pixels;
    }
}


class GreenOnly extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y]=makePixel(0, getGreen(pixels[x][y]), 0);
            }
        }
        return pixels;
    }
}


class BlueOnly extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y]=makePixel(0, 0, getBlue(pixels[x][y]));
            }
        }
        return pixels;
    }
}

class BlackAndWhite extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	int avg=getRed(pixels[x][y])+getBlue(pixels[x][y])+getGreen(pixels[x][y]);
            	avg=avg/3;
            	pixels[x][y]=makePixel(avg, avg, avg);
            }
        }
        return pixels;
    }
}


class VerticalReflect extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height/2; y++) {
                int j = pixels[x][height-1-y];
		pixels[x][height-1-y]=pixels[x][y];
		pixels[x][y]=j;
            }
        }
        return pixels;
    }
}


class HorizontalReflect extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width/2; x++) {
                int j = pixels[width-1-x][y];
                pixels[width-1-x][y]=pixels[x][y];
                pixels[x][y]=j;
            }
        }
        return pixels;
    }
}


class Grow extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

	int[][] pixels2 = new int[width*2][height*2];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels2[2*x][2*y]=pixels[x][y];
                pixels2[2*x+1][2*y]=pixels[x][y];
                pixels2[2*x][2*y+1]=pixels[x][y];
                pixels2[2*x+1][2*y+1]=pixels[x][y];
            }
        }
        return pixels2;
    }
}


class Shrink extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;
        
        if (width==1){int [][] pixels3= new int [1][height/2];
        for (int y = 0; y < height/2; y++)
        	{pixels3[0][y]=(pixels[0][2*y]+pixels[0][2*y+1])/2;}
        return pixels3;}
        
        else if (height==1){int [][] pixels3= new int [width/2][1];
        for (int x = 0; x < width/2; x++)
        	{pixels3[x][0]=(pixels[2*x][0]+pixels[2*x+1][0])/2;}
        return pixels3;}
       
        else {
        int[][] pixels3 = new int[width/2][height/2];
        for (int x = 0; x < width/2; x++) {
            for (int y = 0; y < height/2; y++) {
                pixels3[x][y]=(pixels[2*x][2*y]+pixels[2*x+1][2*y]+pixels[2*x][2*y+1]+pixels[2*x+1][2*y+1])/4;
            }
        }
        
        return pixels3;
    }
}
}
    
    class BurntOrange extends ImageEffect {
    	
    	public int[][] apply(int[][] pixels,
                ArrayList<ImageEffectParam> params) {
    		int width = pixels[0].length;
    		int height = pixels.length;

    		for (int x = 0; x < width; x++) {
    			for (int y = 0; y < height; y++) {
    				int red=getRed(pixels[x][y]);
    				int green=getGreen(pixels[x][y]);
    				int blue=getBlue(pixels[x][y]);
    				if(red>=green&&red>=blue)
    				{pixels[x][y]=makePixel(red,red/2,0);}
    				else if(green>=red&&green>=blue)
    				{pixels[x][y]=makePixel(255,255,255);}
    				else if(blue>=red&&blue>=green)
    				{pixels[x][y]=makePixel((blue*5)/7,(blue*6)/7,blue);}
    			}
    		}
    		return pixels;
    	}
    }

class Threshold extends ImageEffect {
	
	public Threshold() {
        super();
        params = new ArrayList<ImageEffectParam>();
        params.add(new ImageEffectParam("Threshhold value",
                                           "Minimum RGB value",
                                           127, 0, 255));
    }
	
	public int[][] apply(int[][] pixels,
            ArrayList<ImageEffectParam> params) {
		int width = pixels[0].length;
		int height = pixels.length;
		ImageEffectParam qwert = params.get(0);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int as=0,df=0,gh=0;
				if(getRed(pixels[x][y])>qwert.getValue()){as=255;}
				if(getGreen(pixels[x][y])>qwert.getValue()){df=255;}
				if(getBlue(pixels[x][y])>qwert.getValue()){gh=255;}
				pixels[x][y]=makePixel(as,df,gh);
			}
		}
		return pixels;
	}
}

class RedGreenBLue extends ImageEffect {
	
	public int[][] apply(int[][] pixels,
            ArrayList<ImageEffectParam> params) {
		int width = pixels[0].length;
		int height = pixels.length;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int red=getRed(pixels[x][y]);
				int green=getGreen(pixels[x][y]);
				int blue=getBlue(pixels[x][y]);
				if(red>=green&&red>=blue)
				{pixels[x][y]=makePixel(red,0,0);}
				else if(green>=red&&green>=blue)
				{pixels[x][y]=makePixel(0,green,0);}
				else if(blue>=red&&blue>=green)
				{pixels[x][y]=makePixel(0,0,blue);}
			}
		}
		return pixels;
	}
}

class RedWhiteAndBlue extends ImageEffect {
	
	public int[][] apply(int[][] pixels,
            ArrayList<ImageEffectParam> params) {
		int width = pixels[0].length;
		int height = pixels.length;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int red=getRed(pixels[x][y]);
				int green=getGreen(pixels[x][y]);
				int blue=getBlue(pixels[x][y]);
				if(red>=green&&red>=blue)
				{pixels[x][y]=makePixel(red,0,0);}
				else if(green>=red&&green>=blue)
				{pixels[x][y]=makePixel(255,255,255);}
				else if(blue>=red&&blue>=green)
				{pixels[x][y]=makePixel(0,0,blue);}
			}
		}
		return pixels;
	}
}

class Cycle1 extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y]=makePixel(getGreen(pixels[x][y]), getBlue(pixels[x][y]), getRed(pixels[x][y]));
            }
        }
        return pixels;
    }
}

class Cycle2 extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                pixels[x][y]=makePixel(getBlue(pixels[x][y]), getRed(pixels[x][y]), getGreen(pixels[x][y]));
            }
        }
        return pixels;
    }
}

class stretchHorizontal extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int width = pixels[0].length;
        int height = pixels.length;
        int[][] pixels4 = new int[width][height];
        for (int x = 0; x < width; x++){
        	for (int y = 0; y < height; y++)
        	{
        		pixels4[x][y]=makePixel(0,0,0);
        	}
        }

        for (int x = 0; x < width; x++) {
            for (int y = height/4; y <= height/2; y++) {
                pixels4[x][y]=pixels[x][y+((height/2)-y)/2];
            }
            for (int y = (3*height)/4; y >= height/2; y--) {
                pixels4[x][y]=pixels[x][y-(y-(height/2))/2];
            }
            for (int y = 0; y < height/4; y++) {
                pixels4[x][y]=pixels[x][(3*y)/2];
            }
            for (int y = (3*height)/4; y < height; y++) {
                pixels4[x][y]=pixels[x][height-(3*(height-y))/2];
            }
        }
        return pixels4;
    }
}

class stretchVertical extends ImageEffect {

    public int[][] apply(int[][] pixels,
                         ArrayList<ImageEffectParam> params) {
        int height = pixels[0].length;
        int width = pixels.length;
        int[][] pixels4 = new int[width][height];
        for (int x = 0; x < width; x++){
        	for (int y = 0; y < height; y++)
        	{
        		pixels4[x][y]=makePixel(0,0,0);
        	}
        }

        for (int y = 0; y < width; y++) {
            for (int x = height/4; x <= height/2; x++) {
                pixels4[x][y]=pixels[x+((height/2)-x)/2][y];
            }
            for (int x = (3*height)/4; x >= height/2; x--) {
                pixels4[x][y]=pixels[x-(x-(height/2))/2][y];
            }
            for (int x = 0; x < height/4; x++) {
                pixels4[x][y]=pixels[(3*x)/2][y];
            }
            for (int x = (3*height)/4; x < height; x++) {
                pixels4[x][y]=pixels[height-(3*(height-x))/2][y];
            }
        }
        return pixels4;
    }
}
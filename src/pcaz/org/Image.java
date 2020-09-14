package pcaz.org;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
 * 
 * @author pascaz10
 *
 */
public class Image {
	
	int[] image;
	int[] red;
	int[] green;
	int[] blue;
	int[] alpha;
	int w;
	int h;
	BufferedImage bf;
	
	
	
	public Image() {
           
	}
	/**
	 * loadImage load an image file
	 * @param fileIn
	 */
	public void loadImage(String fileIn) {
		
		BufferedImage buf=null;
		
		try {
			buf = ImageIO.read(new File(fileIn));
		 }
		 catch (Exception e )
		 {
			 e.printStackTrace();
		 }
		 w = buf.getWidth();
		 h = buf.getHeight();
		 bf= new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		 
		 // copy the buffer in bf
		 
		 for(int j=0; j<h;j++) {
			 for(int i=0; i<w; i++){
				 bf.setRGB(i,j,buf.getRGB(i,j));
			 }

		 }
		
		// create news buffers for colors
		 
		 red = new int[w*h];
		 green = new int[w*h];
		 blue = new int[w*h];
		 alpha = new int[w*h];
		 
	}
	
	/**
	 * getImage: get the buffer
	 * 
	 * @return image 
	 */
	public int[] getImage() {
		
		 image= bf.getRGB(0,0,w,h,null,0,w);
/*		 
// another solution
 		 for(int j=0; j<h;j++) {
			 for(int i=0;i<w; i++) {
				 	     Color c = new Color(image[j*w+i]);
						 red[j*w+i]= c.getRed();
				 		 green[j*w+i] = c.getGreen();
				 		 blue[j*w+i] = c.getBlue();
				 		 alpha[j*w+i] = c.getAlpha();
			 }
		 }
*/	 
		 // all colors in buffers for all bits
		 
		 for(int j=0; j<h;j++) {
			 for(int i=0;i<w; i++) {
						 blue[j*w+i]= (image[j*w+i] & 0x000000ff) >>> 0;
				 		 green[j*w+i] = (image[j*w+i] & 0x0000ff00) >>> 8;
				 		 red[j*w+i] = (image[j*w+i] & 0x00ff0000) >>> 16;
				 		 alpha[j*w+i] = (image[j*w+i] & 0xff000000) >>> 24;
			 }
		 }
		 
		 return image;
		 
		
	}
	
	/**
	 * setImage Set a new buffer for an image
	 * 
	 * @param red red buffer
	 * @param green green buffer
	 * @param blue blue buffer
	 * @param alpha alpha buffer
	 * @return
	 */
	public int[] setImage( int[] red, int[] green, int[] blue, int[] alpha) {
		
	int[] buf;
	buf = new int[w*h];
	
	// create the image buffer composite
	for(int j=0; j<h ; j++) {
		for(int i=0; i<w ; i++) {
			buf[j*w+i] = blue[j*w+i] + (green[j*w+i] << 8) + (red[j*w+i] << 16) + (alpha[j*w+i] << 24); 
		}
	}
	
	// the set it in bf
	bf.getRaster().setDataElements(0,0,w,h,buf); 
	return buf;
	}
	
	
	/**
	 * saveImage: Save an image buffer to a file
	 * 
	 * @param fileOut
	 * @param format (today only png)
	 * @param buf
	 */
	public void saveImage(String fileOut, String format,int[] buf) {

	//create a new buffered image (RGB without transparency)		
	BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	img.getRaster().setDataElements(0,0,w,h,buf);
	 
	// write new file 
	try {
		File outputFile = new File(fileOut);
		ImageIO.write(img, format, outputFile);
	}
	catch (Exception e)
	{ 
		e.printStackTrace();	 
			 
	}		
	
	return ;
	}

	
	
	public int[] getRed(){
		
		return red;
	}
	
	public int[] getGreen(){
		
		return green;
	}
	
	public int[] getBlue(){
		
		
		return blue;
	}
	public int[] getAlpha(){
		
	
		return alpha;
	}
	
	
	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}


}

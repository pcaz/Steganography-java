package pcaz.org;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

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
		 
		 for(int j=0; j<h;j++) {
			 for(int i=0; i<w; i++){
				 bf.setRGB(i,j,buf.getRGB(i,j));
			 }

		 }
		
		
		 red = new int[w*h];
		 green = new int[w*h];
		 blue = new int[w*h];
		 alpha = new int[w*h];
		 
	}
	
	public int[] getImage() {
		
		 image= bf.getRGB(0,0,w,h,null,0,w);
/*		 
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
	public int[] setImage( int[] red, int[] green, int[] blue, int[] alpha) {
		
	int[] buf;
	buf = new int[w*h];
	for(int j=0; j<h ; j++) {
		for(int i=0; i<w ; i++) {
			buf[j*w+i] = blue[j*w+i] + (green[j*w+i] << 8) + (red[j*w+i] << 16) + (alpha[j*w+i] << 24); 
		}
	}
	bf.getRaster().setDataElements(0,0,w,h,buf); 
	return buf;
	}
	
	
	public void saveImage(String fileOut, String format,int[] buf) {

			
	BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
	img.getRaster().setDataElements(0,0,w,h,buf);
	//new_buf = (int[])img.getRaster().getDataElements(0,0,w,h,buf);
	 
	 int[] val= new int[w*h];
	 int[] new_buf = new int[w*h];
	
	 new_buf = img.getRGB(0,0,w,h,null,0,w);
	 val=bf.getRGB(0,0,w,h,null,0,w);
	 for (int j=0; j< h; j++) {
		 for(int i=0; i<w; i++) {
			 if (new_buf[j*w+i] != val[j*w+i]) {
				 System.out.println("i= "+i+" j= "+j+" buf = "+new_buf[j*w+i] +" val= "+val[j*w+i]);	
			 }
		 }
	}
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

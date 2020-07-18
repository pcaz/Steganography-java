package pcaz.org;

import pcaz.org.Image;
import java.util.BitSet;
import pcaz.org.Bits;



public class Codage {
	String fileIn;
	String fileOut;
	String msg;
	int line;
	Image Image;
	int[] img;
	int[] old;
	
	public Codage(String fileIn, String fileOut, String msg, int line) {
		this.fileIn = fileIn;
		this.fileOut = fileOut;
		this.msg = msg;
		this.line = line;
		Image = new Image();
	

	}
	 
	public void apply() {
	    Image.loadImage(fileIn);
		int w = Image.getW();
		int h = Image.getH();
		
		old=Image.getImage();
		if((line < 1) || (line > h)) throw new IllegalArgumentException();
		
		// initlals cooordonnees (beginning of line)
		
		int initial = (line-1) * w;
		
		int[] red=Image.getRed();
		int[] green=Image.getGreen();
		int[] blue=Image.getBlue();
		int[] alpha=Image.getAlpha();
		
		// Message length
		System.out.println("W="+w+", h="+h+", line="+line+", initial="+initial);
	
		BitSet bits = Bits.convert(msg.length());

        
		
        int[] a;
        int[] b;
        int[] c;
        int[] d;
        
        a = new int[8];
        b = new int[8];
        c = new int[8];
        d = new int[8];
        
		
        for(int i=0; i<8; ++i) {
   		 a[i] = ((red[i+initial]/2)*2)+(bits.get(i) ? 1 : 0);
   	    }
   		
      /*  for(int i=0; i<8; ++i) {
      		 b[i] = (a[i]/2) * 2;
      	    }
        for(int i=0; i<8; ++i) {
      		 c[i] = (bits.get(i) ? 1 : 0);
      	    }
        for(int i=0; i<8; ++i) {
      		 d[i] = b[i]+c[i];
      	    }
     */
        for(int i=0;i<8;i++) System.out.println(red[i+initial]+","+a[i]);
        for(int i=0; i<8; i++) red[i+initial]=a[i]	;	
        Bits.convert(msg);
        
        img=Image.setImage(red,green,blue, alpha);
        
/*        for(int i=0; i<w*h; i++) {
        	if(old[i] != img[i]) {
        		System.out.println("i="+i+" old[i)= "+old[i]+ " , img[i]= "+img[i]);
        	}
        }
*/
		Image.saveImage(fileOut, "png",img);
	    
	}
		
		
	
}

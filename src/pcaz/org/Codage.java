package pcaz.org;


import java.util.BitSet;
/**
 * 
 * @author pascaz10
 *
 */



public class Codage {
	String fileIn;
	String fileOut;
	String msg;
	int line;
	Image Image;
	int[] img;

	/**
	 * Codage
	 * 
	 * @param fileIn file in
	 * @param fileOut file out
	 * @param msg message to insert
	 * @param line insert line (1 default)
	 * 
	 * @return a Codage object
	 */
	public Codage(String fileIn, String fileOut, String msg, int line) {
		this.fileIn = fileIn;
		this.fileOut = fileOut;
		this.msg = msg;
		this.line = line;
		Image = new Image();
	

	}
	/**
	 * Apply
	 * 
	 *  Codage application on "filein", line "line"  with message "msg"
	 */
	public void apply() {
		
		// first, we load image content in filein
	    Image.loadImage(fileIn);
		int w = Image.getW();
		int h = Image.getH();
		// then, we get it 
		Image.getImage();
		
		// if line is'nt in image, error
		
		if((line < 1) || (line > h)) throw new IllegalArgumentException();
		
		// initials coordinates (beginning of line)
		
		int initial = (line-1) * w;
		
		// the four color
		
		int[] red=Image.getRed();
		int[] green=Image.getGreen();
		int[] blue=Image.getBlue();
		int[] alpha=Image.getAlpha();
		
		// Message length (bits return bytes in reverse order) 100 is coded 001
		
//		System.out.println("W="+w+", h="+h+", line="+line+", initial="+initial);
	
		BitSet bits = Bits.convert(msg.length());
		    
        int[] a = new int[8];
        
        // we use red channel and save bytes in logical order. 
        
        for(int i=0; i<8; ++i) {
   		 a[i] = ((red[i+initial]/2)*2)+(bits.get(i) ? 1 : 0);
   		 red[i+initial] = a[i]; 
//   		 System.out.println(a[i]);
   	    }
   		
        
        // then convert message with same conventions
        
        // convert String in Bytes array
        byte[] message=msg.getBytes();
        
        int i=8;	// line in image
        int j=0;	// character in message (eight bits)
        while( j <msg.length()) 
        {
        	// convert the Jeme character...
        	bits = Bits.convert(message[j]);
        	// and the eight bits
        	for(int k=0; k<8;k++) {
        		red[i+k+initial] =(red[i+k+initial]/2)*2;
        		if (bits.get(k)) 
        			red[i+k+initial]+=1;
        		
//        		System.out.println("i="+i+", j="+j+", k="+k+", offset="+(i+k+initial)+", red="+ red[i+k+initial]);
        	}
        	j++;	// implement character in message
       	    i=i+8;  // and offset in image
        	
        }
        
        // set new image
        img=Image.setImage(red,green,blue, alpha);
        
        // and save it in fileOut (only 'png' file is supported)
       
		Image.saveImage(fileOut, "png",img);
	    
	}
		
		
	
}

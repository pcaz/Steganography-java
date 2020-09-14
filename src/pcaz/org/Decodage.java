package pcaz.org;

import java.util.BitSet;
/**
 * 
 * @author pascaz10
 *
 */
public class Decodage {
	String fileIn;
	int line;
	Image Image;
	int[] img;
	int msglength;
    StringBuilder msg = new StringBuilder();
    /**
     * Decodage
     * 
     * @param fileIn File with message
     * @param line Line of beginning of message in the file
     * 
     * @return object Decodage
     */
	public Decodage(String fileIn, int line) {
		this.fileIn = fileIn;
		this.line = line;
		Image = new Image();
	}
	
	/**
	 * Apply
	 * 
	 * Apply the Décodage used
	 * 
	 * @return message (String)
	 */
	public String apply() {
		
		// load file containing image end message
		
		  Image.loadImage(fileIn);
			int w = Image.getW();
			int h = Image.getH();
			
		// line
			
			if((line < 1) || (line > h)) throw new IllegalArgumentException();
			
			// initials cooordinates (beginning of line)
			
			int initial = (line-1) * w;
		// get image
			Image.getImage();
		// and the four colors	
			int[] red=Image.getRed();
			int[] green=Image.getGreen();
			int[] blue=Image.getBlue();
			int[] alpha=Image.getAlpha();
			
			
			
			
			
			// Message length (we use red channels, the length is in the eights 
			// first bytes in the line
			
			BitSet bits = new BitSet(8); // all initialized to 0
			for(int i=0; i<8; ++i) {
		   		  if((red[initial+i]%2) == 1) {
		   			  bits.set(i,true);
		 		  }
//		   		System.out.println("Red= "+red[initial+i]+", i= "+bits.get(i));
	   	    }
		msglength=Bits.convert(bits);	
		
		// Get message
		
		int i=8;	// line
	    char c;		// character in use
		
	    do {
			bits = new BitSet(8);
			for(int j=0; j<8;j++) {
				if((red[initial+i+j]%2) == 1) {
	   			  bits.set(j,true);
				}
				else {
					bits.set(j,false);
				}
//				System.out.println("i= "+i+", j="+j+", offset="+(initial+i+j)+", red="+red[initial+i+j]);	
			}
			
		// translate bits in character
			
		c= Bits.toChar(bits);
		
		// and append it
		msg.append(c);
	    i=i+8;	// Next eights bits		
		
		}while ((i-8) < msglength*8);
		
		
			
		
		// ok, we have the message in msg, return it.
		 
		return msg.toString();
	}

}

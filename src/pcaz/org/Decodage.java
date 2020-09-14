package pcaz.org;

import java.util.BitSet;

public class Decodage {
	String fileIn;
	int line;
	Image Image;
	int[] img;
	int msglength;
    StringBuilder msg = new StringBuilder();
    
	public Decodage(String fileIn, int line) {
		this.fileIn = fileIn;
		this.line = line;
		Image = new Image();
	}
	
	public String apply() {
		
		  Image.loadImage(fileIn);
			int w = Image.getW();
			int h = Image.getH();
			
			
			if((line < 1) || (line > h)) throw new IllegalArgumentException();
			
			// initlals cooordonnees (beginning of line)
			
			int initial = (line-1) * w;
			
			Image.getImage();
			int[] red=Image.getRed();
			int[] green=Image.getGreen();
			int[] blue=Image.getBlue();
			int[] alpha=Image.getAlpha();
			
			
			
			BitSet bits = new BitSet(8); // all initialized to 0
			
			// Message length
			for(int i=0; i<8; ++i) {
		   		  if((red[initial+i]%2) == 1) {
		   			  bits.set(i,true);
		 		  }
		   		System.out.println("Red= "+red[initial+i]+", i= "+bits.get(i));
	   	    }
		msglength=Bits.convert(bits);	
		
		
		int i=8;
	    char c;
		do {
			bits = new BitSet(8);
			for(int j=0; j<8;j++) {
				if((red[initial+i+j]%2) == 1) {
	   			  bits.set(j,true);
				}
				else {
					bits.set(j,false);
				}
				System.out.println("i= "+i+", j="+j+", offset="+(initial+i+j)+", red="+red[initial+i+j]);	
			}
			
	
		c= Bits.toChar(bits);
		msg.append(c);
		
			System.out.print("i="+ (i-8) +" ");
			
			for(int k=0; k<8; k++) {  	
				if(bits.get(k)) {
				  System.out.print("1");
				}
				else {
				  System.out.print("0");
				}
		}
		
	        System.out.print(", "+c);
			System.out.println();	
		
	    i=i+8;		
		
		}while ((i-8) < msglength*8);
		
		
			
		
		
		 
			
		System.out.println(msg.toString());
		return msg.toString();
	}

}

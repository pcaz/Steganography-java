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
		   	
	   	    }
		msglength=Bits.convert(bits);	
		
		
		
		for(int i=8 ;i < msglength+8;i++) {
			bits = new BitSet(8);
			for(int j=0; j<8;j++) {
				if((red[initial+i+j]%2) == 1) {
	   			  bits.set(j,true);
				}
				else {
					bits.set(j,false);
				}
			}
			System.out.print("i="+ (i-8) +" ");
			for(i=initial; i<initial+msglength; i++) {
	   			for(int k=0; k<8; k++) {  
	   				
				if(bits.get(k)) {
	   				  System.out.print("1");
	   			  }
	   			  else {
	   				  System.out.print("0");
	   			  }
				
				}
	   			System.out.println();
			
			}
		}
			
		System.out.println(msg.toString());
		return msg.toString();
	}

}

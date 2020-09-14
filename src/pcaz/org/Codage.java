package pcaz.org;


import java.util.BitSet;




public class Codage {
	String fileIn;
	String fileOut;
	String msg;
	int line;
	Image Image;
	int[] img;

	
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
		Image.getImage();
		
		if((line < 1) || (line > h)) throw new IllegalArgumentException();
		
		// initlals cooordonnees (beginning of line)
		
		int initial = (line-1) * w;
		
		int[] red=Image.getRed();
		int[] green=Image.getGreen();
		int[] blue=Image.getBlue();
		int[] alpha=Image.getAlpha();
		
		// Message length
//		System.out.println("W="+w+", h="+h+", line="+line+", initial="+initial);
	
		BitSet bits = Bits.convert(msg.length());
		    
        int[] a = new int[8];
          	
        for(int i=0; i<8; ++i) {
   		 a[i] = ((red[i+initial]/2)*2)+(bits.get(i) ? 1 : 0);
   		 red[i+initial] = a[i]; 
//   		 System.out.println(a[i]);
   	    }
   		
        //for(int i=0; i<8; i++) red[i+initial]=a[i]	;	
        
        // convert message+
        
        byte[] message=msg.getBytes();
        int i=8;
        int j=0;
        while( j <msg.length()) 
        {
        	bits = Bits.convert(message[j]);
        	for(int k=0; k<8;k++) {
        		red[i+k+initial] =(red[i+k+initial]/2)*2;
        		if (bits.get(k)) 
        			red[i+k+initial]+=1;
        		
//        		System.out.println("i="+i+", j="+j+", k="+k+", offset="+(i+k+initial)+", red="+ red[i+k+initial]);
        	}
        	j++;
       	    i=i+8;
        	
        }
/*        for(int i=0; i< msg.length();i++) {
        	
        
        	for(int k=0; k < 8; k++) {
        		a[j+k] = ((red[i+initial]/2)*2)+(bits.get(i-8) ? 1 : 0);
      		 red[i+initial] = a[i];         	
       }
        }
        bits  = Bits.convert(msg);
        a = new int[bits.length()+8];
        
    
        for(int l=8; l < bits.length()+8; l++) {
      		 a[i] = ((red[i+initial]/2)*2)+(bits.get(i-8) ? 1 : 0);
       		 red[i+initial] = a[i];         	
        }
*/        
        img=Image.setImage(red,green,blue, alpha);
        

		Image.saveImage(fileOut, "png",img);
	    
	}
		
		
	
}

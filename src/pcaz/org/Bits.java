package pcaz.org;

import java.util.BitSet;

public class Bits {

	
	
	  public static BitSet convert(int value) {
	    BitSet bits = new BitSet();
	    
	    int index = 7;
	    while (index>=0) {
	      if (value % 2 == 1) {
	    	  bits.set(index);
	      }
	      --index;
	      value = value >>> 1;
	    }
	    
/*	    
	    int index = 0;
	    while (index<8) {
	      if (value % 2 == 1) {
	    	  bits.set(index);
	      }
	      ++index;
	      value = value >>> 1;
	    }
*/	    
	    return bits;
	  }
	  
	  
	  
	  public static BitSet convert(String str) {
		  BitSet bits = new BitSet();
		  byte[] b = str.getBytes();
		  int j = 0; // indice de boucle interne sur bits
		  System.out.println(b.length);
		  for(int i=0; i<str.length(); i++) {
			  
			  int c = b[i];
			  
			  BitSet bb = Bits.convert(c);
              ;
			  for( int k=j,l=0; l < 8; k++, l++) {
				  if (bb.get(l)) bits.set(k);				 
			  }
			  j = j+8; // prochain octet
		  }

		  
		  return bits;
	  }
		  

	  public static int convert(BitSet bits) {
	    int value = 0;
	    
	    for (int i = 0; i < 8; ++i) {
	    value=value<<1;	
	     if (bits.get(i)) {
	    	 value=value+1;
	     }
	      
	  
	   }
       
	    return value;
	  }
	  
	  public static BitSet revert(BitSet x) {
		  
		  BitSet bits = new BitSet();
	      int value = convert(x);
	      int index = 7;
		  while (value != 0) {
		    if (value % 2 != 0) {
		        bits.set(index);
		      }
		      --index;
		      value = value >>> 1;
		    }
		    return bits;
		  }
	  public static char toChar(BitSet bitSet) {
		   int val= convert(bitSet);
		   char result=(char)val;
//		   System.out.println(bitSet.length());
		    result &= Character.MAX_VALUE;
	        return result;
	    }
	}
	  
	
package pcaz.org;

import java.util.BitSet;

public class Bits {

	
	
	  public static BitSet convert(int value) {
	    BitSet bits = new BitSet();
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
	  
	  
	  
	  public static BitSet convert(String str) {
		  BitSet bits = new BitSet();
		  byte[] b = str.getBytes();
		  int j = 0; // indice de boucle interne sur bits
		  
		  for(int i=0; i<str.length(); i++) {
			  
			  int c = b[i];
			  
			  BitSet bb = Bits.convert(c);

			  for( int k=j,l=0; l < 8; k++, l++) {
				  if (bb.get(l)) bits.set(k);				 
			  }
			  j = j+8; // prochain octet
		  }

		  
		  return bits;
	  }
		  

	  public static int convert(BitSet bits) {
	    int value = 0;
	    for (int i = 0; i < bits.length(); ++i) {
	      value += bits.get(i) ? (1 << i) : 0;
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
		  char result = 0;
	        for (int i = 0; i < bitSet.length(); i++) {
	            if (bitSet.get(i)) {
	                result |= (1 << i);
	            }//from ww  w . j a va  2 s.c  om
	        }
	        result &= Character.MAX_VALUE;
	        return result;
	    }
	}
	  
	
package pcaz.org;

import java.util.BitSet;
/**
 * 
 * @author pascaz10
 *
 */
public class Bits {

	
	/**
	 * Convert int to BitSet
	 * 
	 * @param value = int
	 * @return Bitset
	 * 
	 */
	  public static BitSet convert(int value) {
	    
	    // new Bitset (all 0 bits)
		  
		  BitSet bits = new BitSet();
	   
	   // the bits are return in reverse order, from 7 to 0
	    
	    int index = 7;
	    while (index>=0) {
	      if (value % 2 == 1) {
	    	  bits.set(index);
	      }
	      --index;
	      value = value >>> 1;
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

	  /**
	   * Convert Bitset to char
	   * 
	   * @param bitSet
	   * @return char 
	   */
	  
	  public static char toChar(BitSet bitSet) {
		   
		  // first, convert bitset in integer
		   int val= convert(bitSet);
		   // val is a character, convert it then return
		   char result=(char)val;
		    result &= Character.MAX_VALUE;
		    
	        return result;
	    }
	}
	  
	
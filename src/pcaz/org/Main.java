/**
 * 
 */
package pcaz.org;

/**
 * @author pcaz
 *
 */

import pcaz.org.Codage;
import pcaz.org.Decodage;
import java.util.Hashtable;



public class Main {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if ((args.length==0) || (args.length%2 == 0)) {
			Usage();
	}
		  
		
    Hashtable<String,String> ht= new Hashtable<String,String>();
    for (int i=1; i < args.length; i=i+2) {
    ht.put(args[i],args[i+1]);
    }
   
     
   if(args[0].equals("codage")) {
       if((!ht.containsKey("-in")) || (!ht.containsKey("-out")) || (!ht.containsKey("-msg"))) {
			   Usage();
	
			   
   }
    
   int line = 1;    
   if(ht.containsKey("-line")) {
	   line = Integer.parseInt(ht.get("-line"));   
     }
    
  
   Codage codage = new Codage(ht.get("-in"),ht.get("-out"),ht.get("-msg"),line);
   codage.apply();
   }
}

	
	public static void Usage() {
		
		String program_name = System.getProperty("java.class.path");
		program_name = program_name.substring(0,program_name.lastIndexOf(Constraint.file_separator));
		program_name = program_name.substring(program_name.lastIndexOf(Constraint.file_separator)+1);
		
		System.out.println(program_name+": "+"codage/decodage -in fileIn -out fileOut -msg \"message\" [line = x]");
	}

}

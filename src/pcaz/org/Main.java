/**
 * 
 */
package pcaz.org;

/**
 * @author pcaz
 *
 */

import java.util.Hashtable;



public class Main {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if ((args.length==0) || (args.length%2 == 0)) {
			Usage();
			System.exit(0);
	}
		  
		
    Hashtable<String,String> ht= new Hashtable<String,String>();
    for (int i=1; i < args.length; i=i+2) {
    ht.put(args[i],args[i+1]);
    }
   
     
 
   int line = 1;    
   if(ht.containsKey("-line")) {
	   line = Integer.parseInt(ht.get("-line"));   
     }
    
   switch(args[0])
   {
		   
   case "codage":
 
       if((!ht.containsKey("-in")) || (!ht.containsKey("-out")) || (!ht.containsKey("-msg"))) {
			   Usage();
	}
    
       Codage codage = new Codage(ht.get("-in"),ht.get("-out"),ht.get("-msg"),line);
       codage.apply();
       break;
   
  
   case "decodage":
   			
   			if(!ht.containsKey("-in")) {
   					Usage();
   					System.exit(0);
   			}
	  
   			Decodage decodage = new Decodage(ht.get("-in"), line);
   			System.out.println(decodage.apply());
   			break;
   
   default:		
   			Usage();
   }
   
   
}

	
	public static void Usage() {
		
		String program_name = System.getProperty("java.class.path");
		program_name = program_name.substring(0,program_name.lastIndexOf(Constraint.file_separator));
		program_name = program_name.substring(program_name.lastIndexOf(Constraint.file_separator)+1);
		
		System.out.println(program_name+": "+"codage/decodage -in fileIn -out fileOut -msg \"message\" [line x]");
	}

}

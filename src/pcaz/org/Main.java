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
 * Programme principal
 */

	public static void main(String[] args) {

// si pas de paramètres ou un nombre paire de paramètres (le premier étant le codage ou le décodage), on sort.
		
		if ((args.length==0) || (args.length%2 == 0)) {
			Usage();
	}
		  
// on commance par stoquer les arguments dans une map
		
    Hashtable<String,String> ht= new Hashtable<String,String>();
    for (int i=1; i < args.length; i=i+2) {
    ht.put(args[i],args[i+1]);
    }
   
// si "-line" n'est pas présent, on utilise la première ligne     
 
   int line = 1;    
   if(ht.containsKey("-line")) {
	   line = Integer.parseInt(ht.get("-line"));   
     }
    
// La boucle principale d'analyse
   
   switch(args[0])
   {
		   
   case "codage":
	   //si codage, les clés "-in", "-out" et "msg" doivent être présentes
       if((!ht.containsKey("-in")) || (!ht.containsKey("-out")) || (!ht.containsKey("-msg"))) {
			   Usage();
			   
	}
    
       // Ensuite, on applique le codage en cours
       
       Codage codage = new Codage(ht.get("-in"),ht.get("-out"),ht.get("-msg"),line);
       codage.apply();
       break;
   
  
   case "decodage":
	 //si decodage, la clé "-in", doit être présente
   			if(!ht.containsKey("-in")) {
   					Usage();
   					System.exit(0);
   			}
   		 // Ensuite, on applique le decodage
   			Decodage decodage = new Decodage(ht.get("-in"), line);
   			
   			//et on imprime le résultat.
   			System.out.println(decodage.apply());
   			break;
   			
   			// si ni "codage", ni "decodage", erreur !
   default:		
   			Usage();
   }
   
   
}

/**
 * Usage
 * 
 * Imprime l'usage et sort !	
 */
	public static void Usage() {
	
		
		String program_name = System.getProperty("java.class.path");
		program_name = program_name.substring(0,program_name.lastIndexOf(Constraint.file_separator));
		program_name = program_name.substring(program_name.lastIndexOf(Constraint.file_separator)+1);
		
		System.out.println(program_name+": "+"codage/decodage -in fileIn -out fileOut -msg \"message\" [line x]");
		System.exit(0);
	}

}

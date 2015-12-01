package org.opencompare;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;

public class JsonExport {
	
	private int size;
	

    public JsonExport() {
		super();
		this.size = 200;
	}

	public String matrixProduct(PCM pcm) throws IOException{
      	 // Load a PCM
          StringBuilder builbder = new StringBuilder();
//   			pcm = loader.load(pcmFile).get(0).getPcm();
		//   			assertNotNull(pcm);
		   			builbder.append("{\n");
		   			for(int i=0; i<pcm.getProducts().size(); i++){
		   				List<Cell> cells =  pcm.getProducts().get(i).getCells();
		   				builbder.append("{\"nomProduit\": " + "\""+pcm.getProducts().get(i).getName()+"\", \"valeurs\" : [");
		   				for(Cell cl : cells){
		   					builbder.append("{\"nomFeature\": " + "\"" + cl.getFeature().getName()+"\"," + "\"contenuCellule\": " + "\"" + cl.getContent().toString()+ "\"},");
		   				}
		   				builbder.deleteCharAt(builbder.lastIndexOf(","));
		   				builbder.append("]\n");
		   			}
		   			builbder.append("}");
   		return builbder.toString();
      }
    
    public int convertString(String str){
    	int x = 0;
    	try {
    		x= Integer.parseInt(str);
    	} catch (NumberFormatException e) {
    	      //Will Throw exception!
    	      //do something! anything to handle the exception.
    	}
		return x;
    	
    }
    //convertir un string en entier
    public boolean isInteger( String input ) {
    	
	        try {
	        	Float.parseFloat( input );
	            return true;
	        }
	        catch( Exception e ) {
	            return false;
	        }
    	
    }
    
    public String matrixAfficher(PCM pcm,String col1, String col2) throws IOException{
    	
    	// Load a PCM
       StringBuilder builbder = new StringBuilder();
       //assertNotNull(pcm);
		builbder.append("[\n {\"key\": \"Nikon\", \n \"values\" : [");
		
		for(int i=0; i<pcm.getProducts().size(); i++){
			
			List<Cell> cells =  pcm.getProducts().get(i).getCells();
			// deux for pour récuperer le x et y en ordre
			for(Cell cl : cells){
				if(cl.getFeature().getName().equals(col1)){
					 builbder.append("{\"x\":" + "" + cl.getContent().toString()+ ",");
				
				 }
			}
			for(Cell cl : cells){
				if(cl.getFeature().getName().equals(col2)){
				 builbder.append("\"y\":" + "" + cl.getContent().toString()+ ",");
				
				}
			}
			
			
			//ajout test: pour ajouter les autres caracteristiques.
			for(Cell cl : cells){
			
				if(!cl.getFeature().getName().equals(col2) && !cl.getFeature().getName().equals(col1) && !cl.getFeature().getName().equals("LCD monitor") ){
					
					if (isInteger(cl.getContent())){
						builbder.append("\"" + cl.getFeature().getName()+"\" : " + "" + cl.getContent().toString()+ ",");
					}
					else{
						builbder.append("\"" + cl.getFeature().getName()+"\" : " + "\"" + cl.getContent().toString()+ "\",");
					}
				}
			}
			
			
			//pour le modele et la size
			builbder.append("\"modele\" : " + "\""+pcm.getProducts().get(i).getName()+"\" , \"size\" : "+size+" ");
			//builbder.deleteCharAt(builbder.lastIndexOf(","));
			builbder.append("},\n");
			size += 100;
		}
		builbder.deleteCharAt(builbder.lastIndexOf(","));
		builbder.append(" ] \n }]");
 		
 		 		
 		return builbder.toString();
    }
    
/*    public String matrixAfficher(PCM pcm, String col1, String col2) throws IOException{
    	 // Load a PCM
        StringBuilder builbder = new StringBuilder();
         		builbder.append("[\n");
				
				for(int i=0; i<pcm.getProducts().size(); i++){
					List<Cell> cells =  pcm.getProducts().get(i).getCells();
					//builbder.append("{\"nomProduit\": " + "\""+pcm.getProducts().get(i).getName()+"\",");
					builbder.append("{Key: " + "\""+pcm.getProducts().get(i).getName()+"\",");
					
					// deux for pour récuperer le x et y en ordre
					for(Cell cl : cells){
						//if(cl.getFeature().getName().equals(col1) || cl.getFeature().getName().equals(col2))
						//{//builbder.append("\"" + cl.getFeature().getName()+"\":" + "\"" + cl.getContent().toString()+ "\",");
						//pour récupérer le x
						if(cl.getFeature().getName().equals(col1)){
						    builbder.append("x:" + "\"" + cl.getContent().toString()+ "\",");
						    builbder.append("Filter:" + "\"" + cl.getInterpretation().toString().substring(36, 48)+ "\",");
					    }
					}
					for(Cell cl : cells){
						
						if(cl.getFeature().getName().equals(col2)){
						 builbder.append("y:" + "\"" + cl.getContent().toString()+ "\",");
						 builbder.append("Filter:" + "\"" + cl.getInterpretation().toString().substring(36, 47)+ "\",");
						}
					}
					builbder.deleteCharAt(builbder.lastIndexOf(","));
					builbder.append("},\n");
					
				}
				builbder.deleteCharAt(builbder.lastIndexOf(","));
				builbder.append("]");
 		
 	
 		
 		return builbder.toString();
    }*/
}

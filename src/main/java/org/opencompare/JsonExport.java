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

    public String matrixProduct(){
      	 // Load a PCM
          StringBuilder builbder = new StringBuilder();
          File pcmFile = new File("pcms/example.pcm"); PCMLoader loader = new KMFJSONLoader();
         
          PCM pcm;
   		try {
   			pcm = loader.load(pcmFile).get(0).getPcm();
   			assertNotNull(pcm);
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
   		} catch (IOException e) {
   			e.printStackTrace();
   		}
   		return builbder.toString();
      }
    
    public String matrixAfficher(String col1, String col2){
    	 // Load a PCM
        StringBuilder builbder = new StringBuilder();
        File pcmFile = new File("pcms/example.pcm");
        PCMLoader loader = new KMFJSONLoader(); 
        PCM pcm;
 		try {
 			pcm = loader.load(pcmFile).get(0).getPcm();
 			assertNotNull(pcm);
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
				    }
				}
 				for(Cell cl : cells){
					
 					if(cl.getFeature().getName().equals(col2)){
 					 builbder.append("y:" + "\"" + cl.getContent().toString()+ "\",");
 					}
				}
 				builbder.deleteCharAt(builbder.lastIndexOf(","));
 				builbder.append("},\n");
 				
 			}
 			builbder.deleteCharAt(builbder.lastIndexOf(","));
 			builbder.append("]");
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		
 	
 		
 		return builbder.toString();
    }
}

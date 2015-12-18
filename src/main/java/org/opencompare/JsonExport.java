package org.opencompare;

import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.opencompare.api.java.Cell;
import org.opencompare.api.java.PCM;

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
    
   
    
    public String filtreHtml(PCM pcm)
    {
    	ArrayList<String> filtreString ;
		 ArrayList<Float> filtreInteger ;
		 ArrayList<String> filtreBool ;
		 
    	 StringBuilder builbder = new StringBuilder();

    	 builbder.append("<html> \n");
    	 builbder.append("<head><title>Product Chart</title><meta charset=\"utf-8\"><link href=\"css/nv.d3.css\" rel=\"stylesheet\" type=\"text/css\"> <link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\">	<script src=\"js/jquery-1.6.2.min.js\" type=\"text/javascript\"></script><script src=\"js/cufon-yui.js\" type=\"text/javascript\"></script>	<script src=\"js/cufon-replace.js\" type=\"text/javascript\"></script><script src=\"js/Vegur_300.font.js\" type=\"text/javascript\"></script><link href=\"css/page.css\" rel=\"stylesheet\" type=\"text/css\"><script src=\"https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.2/d3.min.js\" charset=\"utf-8\"> </script><script src=\"js/nv.d3.js\"></script><script type=\"text/javascript\" src=\"js/new.js\"></script><script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script></head>");
    	 builbder.append("<body id=\"page1\"> <div class=\"bg\"> \n");
    	 builbder.append("<h2><center> Product Chart ! </center></h2>");
    	 builbder.append("<pre>Caracteristiques </pre>");
    	 builbder.append("<div id='main'><div id=\"test1\" style='height:500px' class='with-3d-shadow with-transitions'> <svg></svg></div> <div id=\"filtre\">");
    	 builbder.append("<table> \n");
    	 builbder.append("<tr> <div>\n");
    	 
    	 for(int i=0; i<pcm.getFeatures().size(); i++){
    		 filtreString = new ArrayList<String>();
    		 filtreInteger = new ArrayList<Float>();
    		 filtreBool = new ArrayList<String>();
    		 //builbder.append("<td></br> \n");
    		 // on met le nom du feature au dessus
    		 builbder.append("<a class=\"b1\">");
    		 builbder.append("<img src=\"images/marker.gif\"> <strong>");
    		 builbder.append(pcm.getFeatures().get(i).getName() + "\n");
    		 builbder.append("</strong>");
    		 builbder.append("</a><div class=\"toggle\" style=\"display:none\">");
    		// builbder.append("</tr> \n");
    		// builbder.append("</br>");
    		 
    		 for(int j=0; j<pcm.getProducts().size(); j++)
			 {
			   if(pcm.getConcreteFeatures().get(i).getCells().get(j).getInterpretation().toString().contains("IntegerValue")||pcm.getConcreteFeatures().get(i).getCells().get(j).getInterpretation().toString().contains("RealValue"))
    		   { if(!filtreInteger.contains(Float.parseFloat(pcm.getConcreteFeatures().get(i).getCells().get(j).getContent())))
    			{
    				filtreInteger.add(Float.parseFloat(pcm.getConcreteFeatures().get(i).getCells().get(j).getContent()));
    			}
   			   }
			   if(pcm.getConcreteFeatures().get(i).getCells().get(j).getInterpretation().toString().contains("StringValue"))
     		   { if(!filtreString.contains(pcm.getConcreteFeatures().get(i).getCells().get(j).getContent()))
     			 {
     				filtreString.add(pcm.getConcreteFeatures().get(i).getCells().get(j).getContent());
     			 }
			   }
			   if(pcm.getConcreteFeatures().get(i).getCells().get(j).getInterpretation().toString().contains("BooleanValue")||pcm.getConcreteFeatures().get(i).getCells().get(j).getInterpretation().toString().contains("Conditional"))
     		   { if(!filtreBool.contains(pcm.getConcreteFeatures().get(i).getCells().get(j).getContent()))
     			 {
     				filtreBool.add(pcm.getConcreteFeatures().get(i).getCells().get(j).getContent());
     			 }
				}
					
    	 }
    		 //test
    		 
    		 
    		 for (int k=0;k<filtreInteger.size();k++)
    		 { 
    		  builbder.append("<tr> \n");
			  Float max =  Collections.max(filtreInteger);
			  Float min =  Collections.min(filtreInteger);
			  builbder.append("<input type=\"range\" value=\"15\" max=\""+max+"\" min=\""+min+"\" step=\"5\"> ");
			  builbder.append("</tr> \n");
			  builbder.append("</div> \n");
			  k=filtreInteger.size();
    		 }
    		 //fin test
    		 
    		
    		 if(!filtreString.isEmpty()){
    			// builbder.append("<tr> \n");
				 builbder.append(" <div id=\"essai\"> \n");
				 builbder.append("<FORM> \n");
				 builbder.append("<Select multiple style=\"width:200px\" name = "+pcm.getFeatures().get(i).getName()+">");

				// builbder.append("<td> \n");
    			 
    			 for (int l=0;l<filtreString.size();l++)
    			 {

    				
    				 builbder.append("<option> "+filtreString.get(l)+" </option> ");
    				
    			 }
    			 
    			 builbder.append("</Select>");  
    			 builbder.append("</FORM> \n");
    			 builbder.append("</div>\n");
    			 builbder.append("</div> \n");
    			 builbder.append("</tr> \n");
    			
    		
    		 }
    		 for (int y=0;y<filtreBool.size();y++)
    		 {
    			 builbder.append("<tr> \n");
    			// builbder.append(pcm.getFeatures().get(i).getName());  //on ajoute les contenus features
    			// builbder.append("<td> \n");
    			 builbder.append("<input type=\"checkbox\" > ");
    			 builbder.append("</tr> \n");
    			 builbder.append("</div> \n");
    			 y = filtreBool.size();
    		 }
    		 
    		// builbder.append("fiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiin");
    		 
    		 
    		 builbder.append("</br> \n");   		 
    		 
    		 }
    	 
    	 builbder.append("\n");
    	 builbder.append("</table> \n");
    	 builbder.append("</div> \n");
    	 builbder.append("</div> \n");
    	 builbder.append("<script> \n");
    	 builbder.append("jQuery(document).ready(function() { jQuery(\".toggle\").hide(); \n");
    	 builbder.append("$(\".b1\").click(function(){ \n");
    	 builbder.append("jQuery(this).next(\".toggle\").toggle(\"fast\");\n");
    	 builbder.append("return false; \n");
    	 builbder.append("}); \n");
    	 builbder.append("}); \n");
    	 builbder.append("</script>\n");
    	 builbder.append("<footer><div class=\"main\"><div class=\"container_12\"><div class=\"wrapper\"></div>	</div></div></footer>	<p> Module PDL - M1 MIAGE </br>ALLAOUI - BOUSLAMA - DIAWARA - ERIKA - GAHIMBARE - NDOUR </p> \n");
    	 builbder.append("<script type=\"text/javascript\"> Cufon.now(); </script>");
    	 builbder.append("</body> \n");
    	 builbder.append("</html> \n");
    	 
    	return builbder.toString();
    }
    
 public String matrixAfficher(PCM pcm,String col1, String col2) throws IOException{
    	
    	// Load a PCM
       StringBuilder builbder = new StringBuilder();
       //assertNotNull(pcm);
		builbder.append("[\n ");//{\"key\": \"Nikon\", \n \"values\" : [");
		
		for(int i=0; i<pcm.getProducts().size(); i++){
			builbder.append("{\"key\":  \""+pcm.getProducts().get(i).getName()+"\", \n \"values\" : [");
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
			builbder.append("}]\n},\n");
			size += 100;
		}
		builbder.deleteCharAt(builbder.lastIndexOf(","));
		builbder.append(" ] ");//\n }]");
 		
 		 		
 		return builbder.toString();
    }
    
 

}

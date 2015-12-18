package org.opencompare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.HTMLExporter;
import org.opencompare.api.java.io.PCMLoader;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlBody;
import com.gargoylesoftware.htmlunit.html.HtmlHead;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;



/**
 * Created by alae on 02/02/15.
 */
public class MyPCMPrinterTest {

    @Test
    public void testMyPCMPrinter() throws IOException {

    	//File nomFichier = new File("PCM.html");
    	//nomFichier.createNewFile();
        // Load a PCM
        File pcmFile = new File("pcms/example.pcm");
        //File pcmFile = new File("pcms/Exemple2.pcm");
        PCMLoader loader = new KMFJSONLoader();
        PCM pcm = loader.load(pcmFile).get(0).getPcm();
        assertNotNull(pcm);
        
        
        HTMLExporter h = new HTMLExporter();
        //sIOMatrix mat = new IOMatrix();
        
     /*   try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter(new File("pcms/PCM.html")));
        	// normalement si le fichier n'existe pas, il est crée à la racine du projet
        	writer.write(h.toHTML(pcm));
        	 
        	writer.close();
        	}
        	catch (IOException e)
        	{
        	e.printStackTrace();
        	}*/
        
        //System.err.println("" + h.toHTML(pcm));
        // Execute the printer
        MyPCMPrinter myPrinter = new MyPCMPrinter();
        //myPrinter.print(pcm);
        //KMFJSONExporter json = new KMFJSONExporter();
        System.out.println();
        
        
        JsonExport jsexport = new JsonExport();
        try{
        	BufferedWriter buffwrit = new BufferedWriter(new FileWriter(new File("pcms/index.html")));
        	//buffwrit.write(jsexport.matrixAfficher("ISO max","ISO min"));
        	buffwrit.write(jsexport.filtreHtml(pcm));
        	buffwrit.close();
        }
        catch(IOException e){
        	e.printStackTrace();
        }
        //System.out.println(jsexport.matrixProduct(pcm));
        //System.out.println(jsexport.filtreHtml(pcm));
        
    }
    
    @Test
    public void testMatrixAfficher() throws IOException{
    	File pcmFile = new File("pcms/example.pcm");
    	//File pcmFile = new File("pcms/Exemple2.pcm");
        PCMLoader loader = new KMFJSONLoader();
        PCM pcm = loader.load(pcmFile).get(0).getPcm();
        assertNotNull(pcm);
    	
        JsonExport jsexport = new JsonExport();
        try{
        	BufferedWriter buffwrit = new BufferedWriter(new FileWriter(new File("pcms/new.json")));
        	//buffwrit.write(jsexport.matrixAfficher("ISO max","ISO min"));
        	buffwrit.write(jsexport.matrixAfficher(pcm, "ISO max","ISO min"));
        	//buffwrit.write(jsexport.matrixAfficher(pcm, "nb de place","nb de porte"));
        	buffwrit.close();
        }
        catch(IOException e){
        	e.printStackTrace();
        }
    	assertNotNull(jsexport.matrixAfficher(pcm, "ISO max","ISO min"));
    	
    	assertTrue(jsexport.matrixAfficher(pcm, "ISO max","ISO min").length()>0);
    	
    	//assertEquals(jsexport.matrixAfficher(pcm, "nb de place","Boite a vitesse").substring(18, 20), "D1");
    	//System.out.println();
    	 System.out.println(jsexport.matrixAfficher(pcm, "ISO max","ISO min"));
    }
    
    @Test
    public void TestHtml() throws Exception {
    	
    	
      
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("file:///C:/Users/Alae/git/Projet1/pcms/jsonfich.html");
            assertEquals(" OKKKK ","Product Chart", page.getTitleText());

     

            final String pageAsXml = page.asXml();
           
           

       
           final HtmlHead head=(HtmlHead) page.getByXPath("//head").get(0);
           System.out.println(head.asXml());
           assertTrue(head.asXml().contains("<title>"));
           assertTrue(head.asXml().contains("</title>"));
           

       
        }
       
            }
   

}

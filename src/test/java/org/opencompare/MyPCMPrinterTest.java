package org.opencompare;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.impl.io.KMFJSONExporter;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.HTMLExporter;
import org.opencompare.api.java.io.IOMatrix;
import org.opencompare.api.java.io.PCMLoader;



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
        PCMLoader loader = new KMFJSONLoader();
        PCM pcm = loader.load(pcmFile).get(0).getPcm();
        assertNotNull(pcm);
        
        
        HTMLExporter h = new HTMLExporter();
        //sIOMatrix mat = new IOMatrix();
        
     /*   try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter(new File("pcms/PCM.html")));
        	// normalement si le fichier n'existe pas, il est cr�e � la racine du projet
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
        	BufferedWriter buffwrit = new BufferedWriter(new FileWriter(new File("pcms/jsonfichier1.json")));
        	buffwrit.write(jsexport.matrixAfficher("ISO max","ISO min"));
        	buffwrit.close();
        }
        catch(IOException e){
        	e.printStackTrace();
        }
        System.out.println(jsexport.matrixAfficher("Focus points","Megapixel"));

    }
    
    @Test
    public void testMatrixAfficher(){
    	JsonExport jsexport = new JsonExport();
    	assertNotNull(jsexport.matrixAfficher("ISO max","ISO min"));
    	
    	assertTrue(jsexport.matrixAfficher("ISO max","ISO min").length()>0);
    	
    	assertEquals(jsexport.matrixAfficher("ISO max","ISO min").substring(18, 20), "D1");
    	//System.out.println();
    	
    }
    
   

}

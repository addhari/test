/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialtrade1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Harry
 */
public class Save extends Thread {
    
    
    String fileName="";
    String data="";
    String baseFileName="C:\\Users\\Harry\\Desktop\\test";
    String id="";
    Save(String filename,String data,String id)
    {
        this.fileName=filename;
        this.data=data;
        this.id=id;
    }
    public void run()
    {
        if(Utilities.saveFiles)
        {
        try
        {
        //    System.out.println("saving to "+new File(baseFileName+"\\"+fileName+".html").getAbsolutePath());
            if(!new File(baseFileName+"\\"+id).isDirectory())new File(baseFileName+"\\"+id).mkdir();
            FileWriter fw = new FileWriter(new File(baseFileName+"\\"+id+"\\"+fileName+".html"));
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(data);
			bw.close();

        }
        catch(Exception m)
        {
            
        }
        }
    }
    
}

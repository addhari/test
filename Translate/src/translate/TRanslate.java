/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 *
 * @author Harry
 */
public class TRanslate extends javax.swing.JFrame {

    /**
     * Creates new form TRanslate
     */
    public TRanslate() {
        initComponents();
        String message="";
        try { 
            post(message);
        } catch (IOException ex) {
            Logger.getLogger(TRanslate.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
 
   
    
    
    public void post(String input) throws IOException
    {
      
      String   response="";
      BufferedReader br = null;

	br = new BufferedReader(new FileReader("c:\\test.txt"));	
	while ((input = br.readLine()) != null) 
                        {
				
			

		
  
        String body="text="+input+"&itc=te-t-i0-und&num=13";
                input=input.replace("  ","+");
        input=input.replace(" ","+");
           input=input.replace(",","+");
           String starting="\",[\"";
           String ending="\"],[],{\"";
        PostMethod Postmethod= new PostMethod("https://inputtools.google.com/request");
        Postmethod.addRequestHeader("Host"," inputtools.google.com");
        Postmethod.addRequestHeader("User-Agent"," Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
        Postmethod.addRequestHeader("Accept","application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,*/*;q=0.5");
        Postmethod.addRequestHeader("Accept-Language","en-US,en;q=0.8");
        Postmethod.addRequestHeader("Accept-Encoding","gzip,deflate,sdch");
        Postmethod.addRequestHeader("X-Client-Data","CKK2yQEIxLbJAQj9lcoB");
        Postmethod.addRequestHeader("Connection","keepalive,Keep-Alive");
          Postmethod.addRequestHeader("Referer","http://www.google.com/intl/te/inputtools/try/");
         Postmethod.addRequestHeader("Cookie"," GMAIL_RTT=732; HSID=AGUw587xCPSUIaDxj; SSID=AkkKuZaUGVqvz06y7; APISID=Ih-jSJ5j_auXMle3/A8M2u_3miTXEymzjb; SAPISID=ejtl9SxGbj105_1t/Ak0S2KFfQxDfzHxmH; SID=DQAAADcCAAA267hpyJDVmhZXfBKMci856rC5Bt20M4X52m9JnFgK_cqo-_tIsepRyfGNIxjXd5KnchoxbIojLNAegGge80QA3iIH7l6RgYRCFC0NUHGNwTge61j3ATgH7WopBDw_V7PDCSXDV82TzvuXc7s0_ryI7UyWCW9pVANyqNqcBPmFGZbFD4ES2s6VOYs6MW9rVsQyKxTgTnJjv9b0P6cUMvpwr1J-HBGX2mMFqup3wsylJ9Rp1sECSd_bdjunrLKoqcumaV_3XBayFBcn4BZhSYOj3PvA5om7YTL7zWXfYQZfC_i35gZ5VKnGVlSU-mfwZ1TJdztdkQWVAj4r6c2D2gBm6ZKl6P4WtZ9tt3UMq2F0Efh1I1tYGKEW-PCb7zSJI2zupoc63k5VNB4qI8eL-3OzAZoR9BitHnRRe2IKkXdpdaW8dUim-18qqaOeLjQYzFmBGAqxNw8cFEGtyuiUyZGZ6xKVHNAAoW1nFb4E9NGoJtlBo0nYf0kL4tg68g-kOc9GK5WD28pKQbHuFvlgieSRsvX1R3FftEZs-FMTU-KEZgniOssOeXLD7Qupnh37KbXRysPpkgNf72JdaIodIALUoM4k-6mHgUU-r0uLXNWYigGhvt72qVcuSH6uBHuoOUSee838n_k0E8-vg6bpPis_dg5A30Aq2wLGtFS5Q72Qw3hflukKGHvL64wW2vEvUZq3xtMS685Yx1_ZN72A8-YbB8lHEARnTLrm9ticp1q4X2Zsiz-wMtrq6qU4pRALEKE; OGP=-5061574:; OGPC=5061940-3:5061952-24:5061574-2:5061975-17:; NID=77=S4GVPPNrqG49rmLeZbOwlcNB_PTo1_UNBiv6k2q3RV1O_RY14vKc25tuRyt2IhIvd3ntYrgMgb1WpPUeonBk3CMik5PuqeEYUkjOH6C0Sdg2Xgbl_DxAja0Z0o-gJBolOvyKBMo1YM8MYDVSBJl4dPVRxVvTyg68tGAUmP6Ic6EwDk25g5nA6DMH8nhiyQOT7cG3Zz1Irt2GRhMvqo6jYugcIUTNnogFDBY2Cy-hw9MbLvM8PX1_KRWhwvgKpnR4SwwcqMhQXDYUAPeYjlRGJysIBsS1E0OIPw8xhcKOAGLmN7Jyuwa9FZsHZlEPBXY; S=grandcentral=bhpk116omdw5GfDWh1bWOQ:billing-ui-v3=-hfCJx7YhccZsFolsRi1KA:billing-ui-v3-efe=-hfCJx7YhccZsFolsRi1KA");
    
        Postmethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
        Postmethod.setRequestBody(body);
        Postmethod.addRequestHeader("Content-Length",""+body.length());
        Postmethod.getFollowRedirects();
        
     
        try
        {
             HttpClient client = new HttpClient();
         
    client.executeMethod(Postmethod);
     
        Reader reader = null;
        
        StringWriter writer = null;
        
        String charset = "UTF-8"; // You should determine it based on response header.
        
        try {
            
            
            InputStream ungzippedResponse =null;
     
                ungzippedResponse = new GZIPInputStream(Postmethod.getResponseBodyAsStream());
           reader = new InputStreamReader(ungzippedResponse, charset);
            
            writer = new StringWriter();
            
            
            char[] buffer = new char[10240];
            
            for (int length = 0; (length = reader.read(buffer)) > 0;) {
                
                writer.write(buffer, 0, length);
            }
            writer.close();
            reader.close();
            response= writer.toString();
            
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        int pos=0;
        int n1=0;int n2=0;
        while(n1>=0)
        {
            n1=response.indexOf(starting,n2);
            n2=response.indexOf(ending,n1);
            if(n1>0&&n2>0)
            System.out.println(response.substring(n1+starting.length(), n2));
           
        }
          
         
            
        }
        catch(Exception m)
        {
            
            m.printStackTrace();
        }
       
                        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Translate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jButton1)
                .addContainerGap(215, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(134, 134, 134))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   
   
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TRanslate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TRanslate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TRanslate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TRanslate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TRanslate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialtrade1;

/**
 *
 * @author Harry
 */
public class CodeGeneator {
    
    
    
        public static void main(String args[]) {
            
            CodeGeneator c=new CodeGeneator();
            c.generate();
            
        }
        
        
        void generate()
        {
            int i=0;
            int ii=1;
            while(i<30)
            {
            String s="if(i=="+i+"){if(message.indexOf(\"submitted\")!=-1)MainWindow.jLabel"+ii+".setForeground(Color.green);if(message.indexOf(\"Done\")!=-1)MainWindow.jLabel"+ii+".setForeground(Color.blue);MainWindow.jLabel"+ii+".setText(message);}";
                System.out.println(s);
                i++;
                ii++;
                
            }
            
            
            
        }
        
}

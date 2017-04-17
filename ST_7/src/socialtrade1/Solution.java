package socialtrade1;

import java.util.*;
import java.nio.charset.StandardCharsets;

public class Solution {
    
    public static void main(String[] args) {
        while(true)
        {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            String[] subset=new String[n];
            int counter=0;
            String[] s = new String[n];
            for(int s_i=0; s_i < n; s_i++){
                s[s_i] = in.next();
            }
            Arrays.sort(s);
            int i=0;
            while(i<s.length-1)
            {
                if(s[i+1].startsWith(s[i]))
                {
                    if(getAscii(s[i])>getAscii(s[i+1]))
                    subset[counter]=s[i];
                    else   
                    subset[counter]=s[i+1];
                    counter++;
                    i+=2;
                }
                else
                {
                    subset[counter]=s[i];
                    
                    System.out.println("-"+subset[counter]);
                    counter++;
                    subset[counter]=s[i+1];
                    System.out.println("-"+subset[counter]);
                    counter++;
                    i+=2;
                }
                
                
                
            }
            if(i<n)subset[counter]=s[i];
            System.out.println("i is "+i);
            i=0;
            String ss="";
            System.out.println("subset "+Arrays.toString(subset) );
            while(subset[i]!=null)
            {
                ss=ss+subset[i];
                i++;
                if(i==subset.length)break;
            }
            System.out.println(ss);
            System.out.println(""+ getAscii(ss));
            p(subset);
            System.out.println("");
        }
    }
    
    
    static  void p(int[] s)
    {
        for(int i:s)
            System.out.print(" "+i);
    }
    static  void p(String[] s)
    {
        for(String i:s)
            System.out.print(" "+i);
    }
    static  void p(String s)
    {
        System.out.println(s);
    }
    static  void pp(String s)
    {
        System.out.print(" "+s+" ");
    }
    static int getAscii(String s)
    {
        int asciiValue=0;
        byte[] ascii = s.getBytes(StandardCharsets.US_ASCII);
        
        for(int i:ascii)asciiValue+=i;
        return asciiValue;
    }
}


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
public class Utilities {
    
    
       static int MaxIdCount=30;
    static  boolean  EnableCache=!true;
        static  boolean  EnablePendingWork=!true;
    static  boolean  saveFiles=true;
    static int server=1;
    static int PostThreadTimeOut=40;
    static int ThreadTimeOut=120;
    static boolean Enable_SuperFast=false;
    static boolean Enable_SuperFast1=false;
    static boolean Enable_unlimted[]=new boolean[MaxIdCount];
     static  int limit=10;
    static  boolean  EnableStackTrace=!true;
    static  boolean  EnableFastMode=!true;
 
    static String[][] getresp=new String[MaxIdCount][1];
    static String[][] Data=new String[MaxIdCount][15];
    static int[] runningState=new int[MaxIdCount];
    static  int[]remaingTime=new int[MaxIdCount];
    static boolean ThreadLogin=true;
    static String[] messages=new String[MaxIdCount];
    static  int maxSleepingTime=30;
    static int[] completed_links=new int[MaxIdCount];
    static String[] userID=new String[MaxIdCount];
      static String[] points=new String[MaxIdCount];
    static String  response="";
    static String[][] ThreadResponse=new String[MaxIdCount][20];
    static  String[] ThreadCookie=new String[MaxIdCount];
}

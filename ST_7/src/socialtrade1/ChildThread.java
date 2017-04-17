/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package socialtrade1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import static socialtrade1.Engine.ThreadStatus;

public class ChildThread  extends Thread{
    String    url="";
    String body="";
    int count=0;
    String Cookie="";
    int PositionNumber=0;
    boolean printResponse=false;
    
    int state=0;
    
    ChildThread(String url,String body,String cookie,int PositionNumber,int state)
            
    {
        this.Cookie=cookie;
        this.body=body;
        this.url=url;
        this.PositionNumber=PositionNumber;
        this.state=state;
    }
       ChildThread(String url,String body,String cookie,int PositionNumber,int state,boolean i)
            
    {
        this.Cookie=cookie;
        this.body=body;
        this.url=url;
        this.PositionNumber=PositionNumber;
        this.state=state;
        printResponse=i;
    }
      ChildThread(String url,String body,String cookie)
            
    {
        this.Cookie=cookie;
        this.body=body;
        this.url=url;
         this.state=200;
    }
    void pp(Exception m)
    {
        if(Utilities.EnableStackTrace)m.printStackTrace();
        else m.getMessage();
    }
    
    public void run()
    {
        if(state!=-1) Engine.ThreadStatus[PositionNumber][state]=-1;
        if(state==-1)updateUI();
        else if(state==0)FetchCookie();
        else Post();
        
    }
    void updateUI()
    {
        try
        {
            while(true)
            {
                Thread.sleep(1000);
                
                for(int i=0;i<Utilities.MaxIdCount;i++)
                {
                  //  String message=Utilities.Data[i][0]+" :: "+Utilities.completed_links[i]+" :: "+Utilities.messages[i]+" :: "+Utilities.points[i];
                     String message=Utilities.Data[i][0]+" :: "+Utilities.messages[i]+" :: "+Utilities.completed_links[i]; 
                    if(i==0){if(message.indexOf("D")!=-1)MainWindow.jLabel1.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel1.setForeground(Color.blue);MainWindow.jLabel1.setText(message);}
                    if(i==1){if(message.indexOf("D")!=-1)MainWindow.jLabel2.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel2.setForeground(Color.blue);MainWindow.jLabel2.setText(message);}
                    if(i==2){if(message.indexOf("D")!=-1)MainWindow.jLabel3.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel3.setForeground(Color.blue);MainWindow.jLabel3.setText(message);}
                    if(i==3){if(message.indexOf("D")!=-1)MainWindow.jLabel4.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel4.setForeground(Color.blue);MainWindow.jLabel4.setText(message);}
                    if(i==4){if(message.indexOf("D")!=-1)MainWindow.jLabel5.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel5.setForeground(Color.blue);MainWindow.jLabel5.setText(message);}
                    if(i==5){if(message.indexOf("D")!=-1)MainWindow.jLabel6.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel6.setForeground(Color.blue);MainWindow.jLabel6.setText(message);}
                    if(i==6){if(message.indexOf("D")!=-1)MainWindow.jLabel7.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel7.setForeground(Color.blue);MainWindow.jLabel7.setText(message);}
                    if(i==7){if(message.indexOf("D")!=-1)MainWindow.jLabel8.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel8.setForeground(Color.blue);MainWindow.jLabel8.setText(message);}
                    if(i==8){if(message.indexOf("D")!=-1)MainWindow.jLabel9.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel9.setForeground(Color.blue);MainWindow.jLabel9.setText(message);}
                    if(i==9){if(message.indexOf("D")!=-1)MainWindow.jLabel10.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel10.setForeground(Color.blue);MainWindow.jLabel10.setText(message);}
                    if(i==10){if(message.indexOf("D")!=-1)MainWindow.jLabel11.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel11.setForeground(Color.blue);MainWindow.jLabel11.setText(message);}
                    if(i==11){if(message.indexOf("D")!=-1)MainWindow.jLabel12.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel12.setForeground(Color.blue);MainWindow.jLabel12.setText(message);}
                    if(i==12){if(message.indexOf("D")!=-1)MainWindow.jLabel13.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel13.setForeground(Color.blue);MainWindow.jLabel13.setText(message);}
                    if(i==13){if(message.indexOf("D")!=-1)MainWindow.jLabel14.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel14.setForeground(Color.blue);MainWindow.jLabel14.setText(message);}
                    if(i==14){if(message.indexOf("D")!=-1)MainWindow.jLabel15.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel15.setForeground(Color.blue);MainWindow.jLabel15.setText(message);}
                    if(i==15){if(message.indexOf("D")!=-1)MainWindow.jLabel16.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel16.setForeground(Color.blue);MainWindow.jLabel16.setText(message);}
                    if(i==16){if(message.indexOf("D")!=-1)MainWindow.jLabel17.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel17.setForeground(Color.blue);MainWindow.jLabel17.setText(message);}
                    if(i==17){if(message.indexOf("D")!=-1)MainWindow.jLabel18.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel18.setForeground(Color.blue);MainWindow.jLabel18.setText(message);}
                    if(i==18){if(message.indexOf("D")!=-1)MainWindow.jLabel19.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel19.setForeground(Color.blue);MainWindow.jLabel19.setText(message);}
                    if(i==19){if(message.indexOf("D")!=-1)MainWindow.jLabel20.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel20.setForeground(Color.blue);MainWindow.jLabel20.setText(message);}
                    if(i==20){if(message.indexOf("D")!=-1)MainWindow.jLabel21.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel21.setForeground(Color.blue);MainWindow.jLabel21.setText(message);}
                    if(i==21){if(message.indexOf("D")!=-1)MainWindow.jLabel22.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel22.setForeground(Color.blue);MainWindow.jLabel22.setText(message);}
                    if(i==22){if(message.indexOf("D")!=-1)MainWindow.jLabel23.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel23.setForeground(Color.blue);MainWindow.jLabel23.setText(message);}
                    if(i==23){if(message.indexOf("D")!=-1)MainWindow.jLabel24.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel24.setForeground(Color.blue);MainWindow.jLabel24.setText(message);}
                    if(i==24){if(message.indexOf("D")!=-1)MainWindow.jLabel25.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel25.setForeground(Color.blue);MainWindow.jLabel25.setText(message);}
                    if(i==25){if(message.indexOf("D")!=-1)MainWindow.jLabel26.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel26.setForeground(Color.blue);MainWindow.jLabel26.setText(message);}
                    if(i==26){if(message.indexOf("D")!=-1)MainWindow.jLabel27.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel27.setForeground(Color.blue);MainWindow.jLabel27.setText(message);}
                    if(i==27){if(message.indexOf("D")!=-1)MainWindow.jLabel28.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel28.setForeground(Color.blue);MainWindow.jLabel28.setText(message);}
                    if(i==28){if(message.indexOf("D")!=-1)MainWindow.jLabel29.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel29.setForeground(Color.blue);MainWindow.jLabel29.setText(message);}
                    if(i==29){if(message.indexOf("D")!=-1)MainWindow.jLabel30.setForeground(Color.green);if(message.indexOf("D")!=-1)MainWindow.jLabel30.setForeground(Color.blue);MainWindow.jLabel30.setText(message);}
                }
                
            }
        } catch (InterruptedException ex) {
        }
    }
    
    
    void Get()
    {
        
        try{
            
            
            
            URL    url1            = new URL( url );
            HttpURLConnection con= (HttpURLConnection) url1.openConnection();
            con.setDoOutput( true );
            con.setInstanceFollowRedirects( true );
            con.setRequestMethod( "GET" );
            con.setRequestProperty( "Content-Type", " application/json; charset=UTF-8");
            con.setRequestProperty( "charset", "utf-8");
            con.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Host","www.frenzzup.com");
            con.setRequestProperty("Cookie",Cookie);
            con.setUseCaches( false );
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            Engine.ThreadStatus[PositionNumber][state]=con.getResponseCode();
            
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
            String res=response1.toString();
            new Save("get11",res).start();
            if(printResponse)System.out.println(res);
            Utilities.ThreadResponse[PositionNumber][state]=res;
            Engine.ThreadStatus[PositionNumber][state]=200;
            new Save("get111",Utilities.ThreadResponse[PositionNumber][state]).start();
            Utilities.getresp[PositionNumber][0]=res;
            in.close();
        }
        catch(Exception m){
            
        }
    }
    void Post()
    {
        try{
            
            byte[] postData       = body.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            
            URL    url1            = new URL( url );
            HttpURLConnection con= (HttpURLConnection) url1.openConnection();
            con.setDoOutput( true );
            con.setInstanceFollowRedirects( true );
            con.setRequestMethod( "POST" );
            con.setRequestProperty( "Content-Type", " application/json; charset=UTF-8");
            con.setRequestProperty( "charset", "utf-8");
            con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            con.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Host","www.frenzzup.com");
            con.setRequestProperty("Cookie",Cookie);
           
            DataOutputStream wr = new DataOutputStream( con.getOutputStream());
            wr.write( postData );
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            Engine.ThreadStatus[PositionNumber][state]=con.getResponseCode();
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
            String resp=response1.toString();
               if(printResponse)System.out.println(resp);
            Utilities.ThreadResponse[PositionNumber][state]=resp;
            in.close();
        }
        catch(Exception m){
            
            
        }
     
    }
    void FetchCookie()
    {
        
        
        try {
            PostMethod      Postmethod= new PostMethod(url);
            Postmethod.addRequestHeader("Host","www.frenzzup.com");
            Postmethod.addRequestHeader("User-Agent"," Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36");
            Postmethod.addRequestHeader("Accept","application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,*/*;q=0.5");
            Postmethod.addRequestHeader("Accept-Language","en-US,en;q=0.8");
            Postmethod.addRequestHeader("Accept-Encoding","gzip,deflate,sdch");
            Postmethod.addRequestHeader("X-Client-Data","CKK2yQEIxLbJAQj9lcoB");
            Postmethod.addRequestHeader("Connection","keepalive,Keep-Alive");
            Postmethod.addRequestHeader("Cookie",Cookie);
            Postmethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
            Postmethod.setRequestBody(body);
            Postmethod.addRequestHeader("Content-Length",""+body.length());
            Postmethod.getFollowRedirects();
            Postmethod.setFollowRedirects(true);
            HttpClient client = new HttpClient();
            int status=    client.executeMethod(Postmethod);
      
            {
                Cookie[] cookies = client.getState().getCookies();
                int i=0;
                String cookie="";
                while(i<cookies.length)
                {
                    cookie=cookie+";"+cookies[i].getName()+"="+cookies[i].getValue();
                    i++;
                }
                
                cookie=cookie.trim();
                
                Utilities.ThreadCookie[PositionNumber]=cookie;
               
                if(cookie.charAt(0)==';')cookie=cookie.substring(1);
                int n1=cookie.indexOf("UserID=");
                int n2=cookie.indexOf("&",n1);
                Utilities.userID[PositionNumber]=cookie.substring(n1+7,n2).trim();
                Engine.ThreadStatus[PositionNumber][state]=status;
            
            Utilities.ThreadResponse[PositionNumber][state]=Postmethod.getResponseBodyAsString();
       
            }
        }
        catch(Exception m)
        {
            System.out.println("status "+Engine.ThreadStatus[PositionNumber][state]);
            System.out.println(Cookie);
            m.printStackTrace();
            
        }
        ;
    }
    
}

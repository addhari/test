/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package socialtrade1;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import org.json.*;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 *
 * @author Harry
 */
public class Engine extends Thread {
    static int[][] ThreadStatus=new int[Utilities.MaxIdCount][20];
    String hfHiddenFieldID="";
    String userId="";
    String  password="";
    String linksData="";
    String viewstateGenerator="";
    String viewstate="";
    String vs="";
    int status=0;
    int PositionNumber=1;
    int remaing_links=0;
    int state=0;
    int runningSerial=0;
    int timeTaken=0;
    String Cookie="";
    String url="";
    String body="";
    String referer="";
    PostMethod Postmethod=null;
    HttpClient Client=null;
    Document doc=null;
    int sleepingTime=30;
    long previousTime=0;
    String[][] links=new String[500][5];
    String NextWorkID="";
    String WorkID="";
    String response="";
    LocalDate indianTime = LocalDate.now(ZoneId.of("Asia/Kolkata"));
    Engine(String uname,String password,int PositionNumber)
    {
        
        
        Utilities.Data[PositionNumber][0]=uname;
        this.PositionNumber=PositionNumber;
        this.password=password;
        state=0;
        sleepingTime=Integer.parseInt(MainWindow.waitingTime.getText().trim());
    }
    
    void restart()
    {
        System.out.println("Restaring.................. "+Utilities.Data[PositionNumber][0]);
        runningSerial=0;
        Utilities.completed_links[PositionNumber]=0;
        
        Utilities.runningState[PositionNumber]=0;
        state=-1;
        
    }
    void login()
    {
        
        url="https://socialtrade.biz/";
        body="__EVENTTARGET=&__EVENTARGUMENT=&__LASTFOCUS=&__VIEWSTATE=&txtEmailID="+Utilities.Data[PositionNumber][0]+"&txtPassword="+encode(password)+"&CndSignIn=Sign+In&HFUserNo=&HFECode=&HFMCode=&HFParentID=&HFUserID=&txtNewsName=&txtNewsEmail=&txtName=&txtEmail=&txtMobile=&txtSponsorID=&txtSponsorName=&ddlPosition=0&txtPasswordSignUp=&txtConfirmPassword=&HFPassword=&txtUserIDRecover=";
        body=body.replace("+","%2B").replace("/","%2F");
        FetchCookie();
        Cookie=Utilities.ThreadCookie[PositionNumber];
        userId=Utilities.userID[PositionNumber];viewstateGenerator=byId("__VIEWSTATEGENERATOR");
        System.out.println("cookie "+Cookie);
        
    }
    
    void earnEpoints()
    {
     int count=0;
        while(status!=200&&count++<6)
        {
            url="http://www.frenzzup.com/User/EarnePoints.aspx/GetWorkHistory";
            body="{ 'kycstatus':'DOCVERIFIED', 'kycAlert':'', 'userId': '"+userId+"'}";
            Post();
            sleep(5);
            System.out.println("kyc again "+status);
        }
        
        
    }
     void FetchCookie()
    {
        
        
        try {
            PostMethod      Postmethod= new PostMethod(url);
            Postmethod.addRequestHeader("Host","socialtrade.biz");
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
                org.apache.commons.httpclient.Cookie[] cookies = client.getState().getCookies();
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
            }
        }
        catch(Exception m)
        {
           // FetchCookie();
            m.printStackTrace();
            
        }
        ;
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
            con.setRequestProperty("Referer","https://www.socialtrade.biz/User/TodayTask179.aspx");
              DataOutputStream wr = new DataOutputStream( con.getOutputStream());
            wr.write( postData );
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
            
            response=response1.toString();
            System.out.println("errrr"+response);
            status=con.getResponseCode();
            System.out.println(status);
            in.close();
        }
        catch(Exception m){
            
            
        }
        
    }
    
    
    public void run()
    {
        
        Automate();
        
    }
    
    
    
    public void Automate()
    {
        
        
        while(state<7)
        {
            
            Utilities.runningState[PositionNumber]=state;
            
            set(""+state);
            if(state==0)
            {
                login();
                state=1;
                earnEpoints();
                ParseJson();
                NextWorkID=links[runningSerial][1];
                
                
            }
            
            if(state==4)
            {
                
                previousTime=new Date().getTime();
                long t1=new Date().getTime();
                
                int nn=0;
                try
                {
                    nn=Integer.parseInt(NextWorkID.trim());
                }
                catch(Exception m)
                {
                    m.printStackTrace();
                    if(Utilities.completed_links[PositionNumber]==0)
                    {
                        System.out.println("restart1");
                        
                        restart();
                    }
                    else
                    {
                        state=10;
                    }
                }
                
                if(nn!=0)
                {
                    updateWork();
                    
                    
                    
                    
                }
                
            }
            if(state==5)
            {
                try
                {
                    int nn=Integer.parseInt(NextWorkID.trim());
                    System.out.println("remaing_links"+remaing_links);
                    
                    if(remaing_links>0)
                    {
                        String t="NextWorkID";
                        int n1=response.indexOf(t);
                        n1=response.indexOf(":",n1+3);
                        int n2=response.indexOf("\",",n1);
                        NextWorkID=response.substring(n1+2, n2);
                        runningSerial++;
                        
                        remaing_links--;
                        Utilities.completed_links[PositionNumber]++;
                        state=3;
                        
                        
                    }
                    if(remaing_links==0)
                    {
                        set("D");
                        sleep(5000);
                        state=20;
                    }
                    else
                    {
                        state++;
                    }
                    
                }
                catch(Exception m)
                {
                    m.printStackTrace();
                    
                    System.out.println("restart2");
                    
                    restart();
                }
                
            }
            else if(state<6)
            {
                state++;
            }
        }
        
    }
    
    
    void save(String s)
    {
        new Save(s,response,Utilities.Data[PositionNumber][0]).start();
    }
    
    void pp(Exception m)
    {
        if(Utilities.EnableStackTrace)
            m.printStackTrace();
        else m.getMessage();
        
    }
    
    void set(String s)
    {
        Utilities.messages[PositionNumber]=s;
    }
    
    
    
    void exit()
    {
        System.exit(0);
    }
    public String encode(String s)
    {
        String returnString="";
        try {
            returnString=  URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  returnString;
    }
    
    void ParseJson()
    {
        save(Utilities.Data[PositionNumber][0]);
        try {
            JSONObject obj = new JSONObject(response);
            
            String pageName= obj.getJSONObject("d").get("TodayTaskLists").toString();
            JSONArray obj1 = new JSONArray(pageName);
            remaing_links=0;
            String stage="A";
            
            for (int i = 0; i < obj1.length(); i++)
            {
                
                if(obj1.getJSONObject(i).getString("Stage").indexOf(stage)!=-1||Utilities.Enable_unlimted[PositionNumber])
                {
                    
                    links[remaing_links][0]=obj1.getJSONObject(i).getString("WorkID");
                    links[remaing_links][2]=obj1.getJSONObject(i).getString("Link");
                    links[remaing_links][3]=obj1.getJSONObject(i).getString("CampaignID");
                    links[remaing_links][4]=obj1.getJSONObject(i).getString("StPageId");
                    try
                    {
                        links[remaing_links][1]=obj1.getJSONObject(i+1).getString("WorkID");
                    }
                    catch(Exception m)
                    {
                        pp(m);
                        links[remaing_links][1]=obj1.getJSONObject(i).getString("WorkID");
                    }
                    
                    remaing_links++;
                }
                else
                {
                    Utilities.completed_links[PositionNumber]++;
                }
            }
            if(remaing_links==0&& Utilities.completed_links[PositionNumber]>0)
            {
                if(Utilities.Enable_unlimted[PositionNumber]==false)
                {
                    System.out.println("getting  position "+PositionNumber);
                    System.out.println("No more links "+Utilities.Enable_unlimted[PositionNumber]);
                    state=20;
                    set("D");
                }
                
                
            }
            
        }
        
        catch (Exception m)
        {
            m.printStackTrace();
            System.out.println("restart1");
            
            restart();
            
        }
    }
    
    
    void updateWork()
    {
        sleep(1);
        set("UW");
        url="http://www.frenzzup.com/User/EarnePoints.aspx/UpdateTaskWork";
        body="{_user: {\"Username\":\""+userId+"\",\"WorkID\":"+links[runningSerial][0]+",\"StPageId\":"+links[runningSerial][4]+",\"PointsType\":1,\"Password\":"+links[runningSerial][3]+",\"Flag\":\"action\",\"NextWorkID\":\""+links[runningSerial][1]+"\"}}" ;
        Post();
        status=ThreadStatus[PositionNumber][state];
    }
    
    
    
    
    void waitForThread()
    {
        
        ThreadStatus[PositionNumber][state]=-1;
        while(ThreadStatus[PositionNumber][state]==-1)
            sleep(1);
        response=Utilities.ThreadResponse[PositionNumber][state];
        status= Engine.ThreadStatus[PositionNumber][state];
    }
    
    private void get()  {
        
        try
        {
            
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Cookie",Cookie);
            con.setRequestProperty("Referer", referer);
            con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response1 = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response1.append(inputLine);
            }
            in.close();
            response=response1.toString();
            System.out.println(response);
            
        }
        catch(Exception m)
        {
            pp(m);
        }
        
    }
    void sleep(int se)
    {
        try
        {
            Thread.sleep(se*1000);
        }
        catch(Exception m)
        {
            pp(m);
        }
    }
    
    String byId(String s)
    {
        Element info=null;
        String returnstring="";
        try
        {
            doc = Jsoup.parse( response);
            info = doc.getElementById(s);
            returnstring=""+info.val().replace("=","%3D");
        }
        catch(Exception m)
        {
            //pp(m);
        }
        return returnstring;
    }
    
    
}

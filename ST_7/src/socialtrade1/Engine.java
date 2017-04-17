/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package socialtrade1;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.*;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;
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
    int sleepingTime=1;
    long previousTime=0;
    String[][] links=new String[500][5];
    String NextWorkID="";
    String WorkID="";
    String response="";
    LocalDate indianTime = LocalDate.now(ZoneId.of("Asia/Kolkata"));
    Engine(String uname,String password,int PositionNumber)
    {
        
        getIndiaTime();
        Utilities.Data[PositionNumber][0]=uname;
        this.PositionNumber=PositionNumber;
        this.password=password;
        state=0;
        sleepingTime=Integer.parseInt(MainWindow.waitingTime.getText().trim());
    }
    
    
    void serilize()
    {
        FileOutputStream fout=null;
        try {
            fout = new FileOutputStream("f.txt");
            ObjectOutputStream out=new ObjectOutputStream(fout);
            out.writeObject(this);  
            out.flush();
        } catch (Exception ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    void tree()
    {
        url="https://www.frenzzup.com/User/MyConnections.aspx/GetUserDetails";
        body="{ 'lblId':'61272985'}";
        PostThread2();
       
    }
    String  getIndiaTime()
    {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        return sd.format(date);
    }
    void restart()
    {
        System.out.println("Restaring.................. "+Utilities.Data[PositionNumber][0]);
        runningSerial=0;
        Utilities.completed_links[PositionNumber]=0;
        Utilities.remaingTime[PositionNumber]=0;
        Utilities.runningState[PositionNumber]=0;
        state=-1;
        
    }
    void login()
    {
     
        url="http://www.frenzzup.com/Default.aspx";
        body="Scriptmngr1=UpdatePanel1%7CCndSignIn&__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=&__VIEWSTATEGENERATOR=&txtName=&txtEmail=&txtMobile=&txtPasswordSignUp=&txtConfirmPassword=&HFUserNo=&HFECode=&HFMCode=&HFParentID=&HFUserID=&txtEmailID="+Utilities.Data[PositionNumber][0]+"&txtPassword="+encode(password)+"&txtaffiliateID=&HFPassword=&txtOTP=&HFUserIDPass=&txtChangepass=&txtchangepassconfirm=&__ASYNCPOST=true&CndSignIn=Sign%20In";
        PostThread();
        Cookie=Utilities.ThreadCookie[PositionNumber];
        url="http://www.frenzzup.com/MyTimeLine.aspx";
        get();
        state=1;
        userId=Utilities.userID[PositionNumber];
        viewstate=byId("__VIEWSTATE");
        viewstateGenerator=byId("__VIEWSTATEGENERATOR");
      url="http://www.frenzzup.com/MyTimeLine.aspx/GetWorkHistory";
      body="{ 'kycstatus':'DOCVERIFIED', 'kycAlert':'', 'userId': '"+userId+"'}";
      PostThread();
       
    }
  
    void earnEpoints()
    {
        url="https://www.frenzzup.com/User/EarnePoints.aspx";
        get();
        save("2_1");
        url="https://www.frenzzup.com/User/EarnePoints.aspx/ApplyBooster";
        body="{ 'userId':'"+userId+"','flag':'isavailablebooster'}";
        PostThread();
        save("2_2");
        url="https://www.frenzzup.com/User/EarnePoints.aspx/GetWorkHistory";
        body="{ 'kycstatus':'DOCVERIFIED', 'kycAlert':'', 'userId': '"+userId+"'}";
        PostThread();
        save("2_3");
        url="";
        body="";
        save("2_4");
        
        
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
            con.setRequestProperty("Referer","https://www.frenzzup.com/User/TodayTask179.aspx");
            
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
        
        int nextId=1;
        while(state<5)
        {
            
            System.out.println(state);
            Utilities.runningState[PositionNumber]=state;
     
            set(""+state);
            if(state==0)
            {
                login();
                
            }
            

            if(state==3)
                
            {
        
                ParseJson();
                
                NextWorkID=links[runningSerial][1];
              
            }
            if(state==4)
            {
                
         
                
                while(remaing_links>0)
                {
                  
                try
                {
                    nextId=Integer.parseInt(NextWorkID.trim());
                }
                catch(Exception m)
                {
                    
                    if(Utilities.completed_links[PositionNumber]==0)
                    {
                      restart();
                    }
                   
                }
                
                if(nextId!=0)
                {
                    if(remaing_links-->0)
                    updateWork();
                
                }
               
                }
                
            }
          
            
      state++;
        }
        set("D");
        System.out.println("done");
        
    }
   
    
    void save(String s)
    {
        new Save(s,response).start();
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
    
    int  timeTaken()
    {
        long  currentTime=new Date().getTime();
        long totalTime=currentTime-previousTime;
        int TotalSeconds=(int)totalTime/1000;
        Utilities.messages[PositionNumber]=""+TotalSeconds+" S";
        
        return TotalSeconds;
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
         //   m.printStackTrace();
            System.out.println(Utilities.Data[PositionNumber][0]+" Error parse json restarting");
            restart();
            
        }
    }
    
    
    void updateWork()
    {
        set("UW");
        url="http://www.frenzzup.com/MyTimeLine.aspx/UpdateTaskWork";
        body="{_user: {\"Username\":\""+userId+"\",\"WorkID\":"+links[runningSerial][0]+",\"StPageId\":\"165394\",\"PointsType\":1,\"Password\":"+links[runningSerial][3]+",\"Flag\":\"action\",\"NextWorkID\":\""+links[runningSerial][1]+"\"}}" ;
        PostThread();
        status=ThreadStatus[PositionNumber][state];
     //   sleep(5);
        System.out.println("uw1");
        runningSerial++;
        Utilities.completed_links[PositionNumber]++;
    }
    
    void superFast()
    {
        
        System.out.println("superfast enabled");
        while(remaing_links>1)
        {
            runningSerial++;
            Utilities.completed_links[PositionNumber]++;
            body="{_user: {\"Username\":\""+userId+"\",\"WorkID\":"+links[runningSerial][0]+",\"CurrentFlag\":\"hand\",\"PointsType\":250,\"Password\":"+links[runningSerial][3]+",\"Flag\":\"hand\",\"NextWorkID\":\""+links[runningSerial][1]+"\"}}" ;
             PostThread();
            System.out.println(remaing_links+"running "+runningSerial);
           remaing_links--;
        }
        
    }
     void superFast1()
    {
        
        System.out.println("superfast1 enabled");
        while(remaing_links>1)
        {
            runningSerial++;
            Utilities.completed_links[PositionNumber]++;
            body="{_user: {\"Username\":\""+userId+"\",\"WorkID\":"+links[runningSerial][0]+",\"CurrentFlag\":\"hand\",\"PointsType\":250,\"Password\":"+links[runningSerial][3]+",\"Flag\":\"hand\",\"NextWorkID\":\""+links[runningSerial][1]+"\"}}" ;
            PostThread1();
            System.out.println(remaing_links+"running "+runningSerial);
            remaing_links--;
        }
     
        
    }
    void unlimtedPoints()
    {
        
        int points=Integer.parseInt(getPoints());
        int remain=Utilities.limit-points;
        
        while(remain>0)
        {
            Utilities.completed_links[PositionNumber]++;
            url="https://www.frenzzup.com/User/MyePoints.aspx/UpdateTaskWork";
            body="{_user: {\"Username\":\""+userId+"\",\"WorkID\":"+links[runningSerial][0]+",\"CurrentFlag\":\"hand\",\"PointsType\":250,\"Password\":"+links[runningSerial][3]+",\"Flag\":\"hand\",\"NextWorkID\":\""+links[runningSerial][1]+"\"}}" ;
          //  Post();
            new ChildThread(url, body, Cookie,PositionNumber,state).start();  
          if(points%10==0)getPoints();
            remain--;
            points++;
            Utilities.points[PositionNumber]=""+points;
        }
        state=27;
    }
    
    public String  getPoints()
    {
        try
        {
            response="0";
            
            url="https://www.frenzzup.com/User/BuyEpoints.aspx/CalculateRedeemePoints";
            body="{'userId':'"+userId+"' }";
            Post();
            response=response.replace("{\"d\":\"","").replace("\"}", "");
            Utilities.points[PositionNumber]=response;
        }
        catch(Exception m)
        {
            
        }
        
        return response.trim();
    }
    
    void epointsHistory()
    {
        try {
            url="https://www.frenzzup.com/User/EarnePoints.aspx/GetEpointsWorkHistoryGrid";
            body="{\"userId\":\""+userId+"\",\"searchString\":\"\",\"searchField\":\"\",\"searchOper\":\"\",\"isSearch\":false,\"numRows\":30,\"page\":1,\"sortField\":\"AssignedDate\",\"sortOrder\":\"desc\"}";
            PostThread();
            System.out.println("resposne s ");
            System.out.println(response);
         
            JSONObject obj = new JSONObject(response);
            String pageName= obj.getJSONObject("d").get("rows").toString();
            JSONArray obj1 = new JSONArray(pageName);
            for(int i=0;i<obj1.length();i++)
            {
                String a= obj1.getJSONObject(i).getString("AssignedDate");
                System.out.println(a);
            }
           
        } catch (JSONException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
   
    void waitForThread()
    {
        
       ThreadStatus[PositionNumber][state]=-1;
        while(ThreadStatus[PositionNumber][state]==-1)
        {
       sleep1(10);
     
        }
        response=Utilities.ThreadResponse[PositionNumber][state];
        status= Engine.ThreadStatus[PositionNumber][state];
    
    }
    
    public void  PostThread()
    {
        response="";
        new ChildThread(url, body, Cookie,PositionNumber,state).start();
        waitForThread();
    }
  public void  PostThread1()
    {
        new ChildThread(url, body, Cookie,PositionNumber,state).start();
       
    }  
  public void  PostThread2()
    {
        new ChildThread(url, body, Cookie,PositionNumber,state,true).start();
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
        void sleep1(int se)
    {
        try
        {
            Thread.sleep(se);
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

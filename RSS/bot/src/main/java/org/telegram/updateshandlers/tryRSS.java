package org.telegram.updateshandlers;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;

//**************By Tyler**********************
public class tryRSS {
	//main: do nothing
    public static void main(String[] args) {
    }
    //function of Reading RSS Feed (by url and tag sensing)
    public static String readRSSFeed(String urlAddress, String event){
        try{
            URL rssUrl = new URL (urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            String sourceCode = "";
            String line;
            boolean flag = false;
            while((line=in.readLine())!=null){
            	if(event=="Weather"){
            		//grab the wanted information and filter out tags
            		if(line.contains("<p>At"))
            		{
            		line = in.readLine();
            		String temp = line;
            		temp = temp.replace("  ", "");
            		temp = "At " + temp;
            		sourceCode += temp;
            		}
            		if(line.contains("<img")){
            			line = in.readLine();
            		}
                if(line.contains("<br/>")){
                    String temp = line;
                    int Pos = temp.indexOf("<br/>");
                    temp = temp.substring(0,Pos);
                    temp=temp.replace("<br/>","");
                    temp=temp.replace("</table>","");
                    temp=temp.replace("  ","");
                    temp=temp.replace("本 港 其 他 地 區 的 氣 溫 ：", "");
                    temp=temp.replace("本 港 其 他 地 区 的 气 温 ：", "");
                    temp=temp.replace("</font>", "");
                    sourceCode +=temp+ "\n" ;
                }
                }
            	else if(event=="Warning"){
            		if(line.contains("<title><![CDATA[")){
            			String temp = line;
                        int lastPos = temp.indexOf("]]></title>");
                        temp = temp.substring(0,lastPos);
                        temp=temp.replace("]]></title>","");
                        temp=temp.replace("<title><![CDATA[","");
                        temp=temp.replace("  ","");
                        sourceCode +=temp+ "\n" ;
            		}
            	}
            	else if(event=="pubDate"){
            		if(line.contains("<pubDate>")){
            			String temp = line;
                        int lastPos = temp.indexOf("</pubDate>");
                        temp = temp.substring(0,lastPos);
                        temp=temp.replace("<pubDate>","");
                        temp=temp.replace("</pubDate","");
                        temp=temp.replace("  ","");
                        sourceCode +=temp+ "\n" ;
            	}
            }
            }
            in.close();
            return sourceCode;
        } catch (MalformedURLException ue){
            System.out.println("Malformed URL");
        } catch (IOException ioe){
            System.out.println("Something went wrong reading the contents");
        }
        return null;
    }
}

//************************************************8
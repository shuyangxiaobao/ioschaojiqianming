package servlet;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 *
 * Cookie快速入门
 *
 */

@WebServlet("/cookieDemo1")
public class CookieDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //获取HTTP请求的输入流
        InputStream is = request.getInputStream();
        //已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader(new InputStreamReader (is,"UTF-8"));
        StringBuilder sb = new StringBuilder();

        //读取HTTP请求内容
        String buffer = null;
        while ((buffer = br.readLine()) != null) {
            sb.append(buffer);
        }
        String content = "";
        if (sb.length () > 0){
             content = sb.toString().substring(sb.toString().indexOf("<?xml"), sb.toString().indexOf("</plist>")+8);
        }
        //content就是接收到的xml字符串
        //进行xml解析即可
        String udid = "";

        int i = content.indexOf ( "<key>UDID</key>\t<string>" );
        int j = content.indexOf ( "</string>\t<key>VERSION" );
        udid = content.substring ( i,j );

        System.out.printf ( "😄1"+udid +"1😄");

        udid = udid.substring ( 24 );
        System.out.printf ( "😄2"+udid +"2😄");


//        Document document = null;
//        try {
//            document = (Document) DocumentHelper.parseText(sb.toString ());
//        } catch (DocumentException e) {
//            e.printStackTrace ();
//        }
//
//        NodeList udid1 = document.getElementsByTagName ( "UDID" );
//        udid = udid1.toString ();


        response.setStatus(301); //301之后iOS设备会自动打开safari浏览器
        response.setHeader("Location", "https://shuyangxiaobao.github.io/ioschaojiqianming?UDID="+udid);
        //http://192.168.1.106:8080/udid.jsp 是用于显示udid的页面,也可以利用之前的下载mobileprofile文件页面


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

//<key>UDID</key>	<string>6399f81e6998772826ddad8ded01cb448d4faedc</string>	<key>VERSION</key>
//
//<key>UDID</key>
//
//    <?xml version="1.0" encoding="UTF-8"?><!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd"><plist version="1.0"><dict>	<key>IMEI</key>	<string>35 534408 429420 9</string>	<key>PRODUCT</key>	<string>iPhone9,1</string>	<key>SERIAL</key>	<string>DNQT1F84HG74</string>	<key>UDID</key>	<string>6399f81e6998772826ddad8ded01cb448d4faedc</string>	<key>VERSION</key>	<string>17F80</string></dict></plist>
//content
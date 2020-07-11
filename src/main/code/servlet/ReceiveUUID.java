package servlet;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;


/**
 * Cookie快速入门
 */

@WebServlet("/receiveUUID")
public class ReceiveUUID extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType ( "text/html;charset=UTF-8" );
        request.setCharacterEncoding ( "UTF-8" );
        //获取HTTP请求的输入流
        InputStream is = request.getInputStream ();
        //已HTTP请求输入流建立一个BufferedReader对象
        BufferedReader br = new BufferedReader ( new InputStreamReader ( is, "UTF-8" ) );
        StringBuilder sb = new StringBuilder ();

        //读取HTTP请求内容
        String buffer = null;
        while ((buffer = br.readLine ()) != null) {
            sb.append ( buffer );
        }
        String content = "";
        if (sb.length () > 0) {
            content = sb.toString ().substring ( sb.toString ().indexOf ( "<?xml" ), sb.toString ().indexOf ( "</plist>" ) + 8 );
        }
        //content就是接收到的xml字符串
        //进行xml解析即可
        String udid = "";

        int i = content.indexOf ( "<key>UDID</key>\t<string>" );
        int j = content.indexOf ( "</string>\t<key>VERSION" );
        udid = content.substring ( i, j );

        udid = udid.substring ( 24 );
        System.out.println ( "😄" + udid + "😄" );


        response.setStatus ( 301 ); //301之后iOS设备会自动打开safari浏览器
        response.setHeader ( "Location", "https://shuyangxiaobao.github.io/ioschaojiqianming?UDID=" + udid );
        //http://192.168.1.106:8080/udid.jsp 是用于显示udid的页面,也可以利用之前的下载mobileprofile文件页面

        FileOutputStream fos = new FileOutputStream ( "1111.txt", false );
        for (int k = 1; k <= 1; k++) {
            fos.write ( udid.getBytes () );
            String name = "\t" + System.currentTimeMillis () + "\n";
            fos.write ( name.getBytes () );
        }
        fos.close ();
        File file = new File ( this.getClass ().getResource ( "/" ).getPath () );

        String rbpath = "/Users/xiaobao/java/Tomcat/apache-tomcat-8.5.51/webapps/ROOT/UpdateProfile.rb";

        try {

            Process p = Runtime.getRuntime().exec("ruby "+rbpath+" true");
            p.waitFor();
            System.out.println(p.exitValue());
        }
        catch (Exception err) {
            err.printStackTrace();
        }

        String signPath = "/Users/xiaobao/java/Tomcat/apache-tomcat-8.5.51/webapps/ROOT/qianming.sh";
        try {

            Process p = Runtime.getRuntime().exec("sh "+signPath);
            p.waitFor();
            System.out.println(p.exitValue());
        }
        catch (Exception err) {
            err.printStackTrace();
        }












    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost ( request, response );
    }
}



//<string>https://shuyangxiaobao.github.io/ioschaojiqianming/src/main/webapp/xlp.ipa</string>
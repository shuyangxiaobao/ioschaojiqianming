package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        if (sb.length () > 0){
            String content = sb.toString().substring(sb.toString().indexOf("<?xml"), sb.toString().indexOf("</plist>")+8);
        }
        //content就是接收到的xml字符串
        //进行xml解析即可
        String udid = "";
        response.setStatus(301); //301之后iOS设备会自动打开safari浏览器
        response.setHeader("Location", "http://192.168.1.106:8080/udid.jsp?UDID="+udid);
        //http://192.168.1.106:8080/udid.jsp 是用于显示udid的页面,也可以利用之前的下载mobileprofile文件页面


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

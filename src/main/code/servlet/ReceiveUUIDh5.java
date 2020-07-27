package servlet;


import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


/**
 * Cookie快速入门
 */

@WebServlet("/receiveUUIDh5")
@SuppressWarnings("deprecation")
public class ReceiveUUIDh5 extends HttpServlet {

    private static final long serialVersionUID = 5522372203700422672L;



    protected void doOptions(HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Origin,X-Requested-With,Content-Type,Accept,"
                + "content-Type,origin,x-requested-with,content-type,accept,authorization,token,id,X-Custom-Header,X-Cookie,Connection,User-Agent,Cookie,*");
        response.setHeader("Access-Control-Request-Headers", "Authorization,Origin, X-Requested-With,content-Type,Accept");
        response.setHeader("Access-Control-Expose-Headers", "*");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType ( "text/html;charset=UTF-8" );

        request.setCharacterEncoding ( "UTF-8" );
        String udid = request.getParameter("udid");


        FileReader fos_success = null;

        try {
            fos_success = new FileReader ( "success.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        }

        char[] cs = new char[1024];//存储读取到的多个字符
        int len = 0;//记录的是每次读取的有效字符个数
        boolean issuccess = false;
        while((len = fos_success.read(cs))!=-1){
            /*
                String类的构造方法
                String(char[] value) 把字符数组转换为字符串
                String(char[] value, int offset, int count) 把字符数组的一部分转换为字符串 offset数组的开始索引 count转换的个数
             */
            String s = new String ( cs, 0, len );
            if (s.contains ( udid )){
                issuccess = true;
                break;
            }
        }
        if (udid.length () < 5){
            response.getWriter().write("下载描述文件88888888");
        } else {
            if (issuccess){
                response.getWriter().write("success");
            } else {
                response.getWriter().write("fail");
            }
        }

        HttpSession session = request.getSession ();
        String result = (String) session.getAttribute ( udid );


//        FileOutputStream fos = new FileOutputStream ( "1111.txt", false );
//        for (int k = 1; k <= 1; k++) {
//            fos.write ( udid.getBytes () );
//            String name = "\t" + System.currentTimeMillis () + "\n";
//            fos.write ( name.getBytes () );
//        }
//        fos.close ();
//        File file = new File ( this.getClass ().getResource ( "/" ).getPath () );
//
//        String rbpath = "/Users/xiaobao/java/Tomcat/apache-tomcat-8.5.51/webapps/ROOT/UpdateProfile.rb";
//
//        try {
//
//            Process p = Runtime.getRuntime().exec("ruby "+rbpath+" true");
//            p.waitFor();
//            System.out.println(p.exitValue());
//        }
//        catch (Exception err) {
//            err.printStackTrace();
//        }
//
//        String signPath = "/Users/xiaobao/java/Tomcat/apache-tomcat-8.5.51/webapps/ROOT/qianming.sh";
//        try {
//
//            Process p = Runtime.getRuntime().exec("sh "+signPath);
//            p.waitFor();
//            System.out.println(p.exitValue());
//        }
//        catch (Exception err) {
//            err.printStackTrace();
//        }


        //输出









    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doOptions ( request,response );
        this.doPost ( request, response );
    }
}



//<string>https://shuyangxiaobao.github.io/ioschaojiqianming/src/main/webapp/xlp.ipa</string>

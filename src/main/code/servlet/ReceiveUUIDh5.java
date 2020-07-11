package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * Cookie快速入门
 */

@WebServlet("/receiveUUIDh5")
public class ReceiveUUIDh5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType ( "text/html;charset=UTF-8" );
        request.setCharacterEncoding ( "UTF-8" );
        String udid = request.getParameter("udid");
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


        //输出
        response.getWriter().write("success");









    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost ( request, response );
    }
}



//<string>https://shuyangxiaobao.github.io/ioschaojiqianming/src/main/webapp/xlp.ipa</string>

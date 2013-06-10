package servlet;

import entities.NavBarEntity;
import entities.SystemEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hugo
 */

@WebServlet(name="SystemServlet", urlPatterns = "/system", asyncSupported = true)
public class SystemServlet extends HttpServlet
{   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        NavBarEntity.setCurrentURL(request.getRequestURL().toString());
        request.getServletContext().getRequestDispatcher("/app/views/partials/system.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        Socket socket;
        PrintWriter out;
        if(action != null)
        {
            socket = new Socket("localhost", 8383);
            out = new PrintWriter(socket.getOutputStream());
            switch(action)
            {
                case "off-system":
                    SystemEntity.setOff(true);
                    out.print(1);
                    break;
                case "on-system":
                    SystemEntity.setOff(false);
                    out.print(2);
                    break;
                case "send-tmin":
                    String strTmin = request.getParameterValues("tmin-field")[0];
                    int tmin = Integer.parseInt(strTmin);
                    SystemEntity.setTmin(tmin);
                    out.print(3);
                    out.print(tmin);
                    break;
                case "send-tmax":
                    String strTmax = request.getParameterValues("tmax-field")[0];
                    int tmax = Integer.parseInt(strTmax);
                    SystemEntity.setTmax(tmax);
                    out.print(4);
                    out.print(tmax);
                    break;
                case "send-led":
                    int led = Integer.parseInt(request.getParameterValues("led")[0]);
                    SystemEntity.setLed(led);
                    out.print(5);
                    out.print(led);
                    out.print(SystemEntity.getLed(led)? 1 : 0);
                    break;
                case "send-push":
                    out.print(6);
                    break;
            }
            out.flush();
            socket.close();
        }
        response.sendRedirect("system");
    }
}

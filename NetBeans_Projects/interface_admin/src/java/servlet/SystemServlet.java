package servlet;

import entities.NavBarEntity;
import entities.SystemEntity;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    //private static final Logger LOG = Logger.getLogger(ModeServlet.class.getName());
    
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
        //LOG.log(Level.SEVERE, action);
        Socket socket;
        PrintWriter out;
        if(action != null)
        {
            socket = new Socket("localhost", 8383);
            out = new PrintWriter(socket.getOutputStream());
            if("off-system".equals(action))
            {
                SystemEntity.setOff(true);
                out.print(1);
            }
            else if("on-system".equals(action))
            {
                SystemEntity.setOff(false);
                out.print(2);
            }
            else if("send-tmin".equals(action))
            {
                String strTmin = request.getParameterValues("tmin-field")[0];
                int tmin = Integer.parseInt(strTmin);
                out.print(3);
                out.print(tmin);
            }
            else if("send-tmax".equals(action))
            {
                String strTmax = request.getParameterValues("tmax-field")[0];
                int tmax = Integer.parseInt(strTmax);
                out.print(4);
                out.print(tmax);
            }
            else if("send-led".equals(action))
            {
                int led = Integer.parseInt(request.getParameterValues("led")[0]);
                SystemEntity.setLed(led);
                out.print(5);
                out.print(led);
                out.print(SystemEntity.getLed(led)? 1 : 0);
            }
            else if("send-push".equals(action))
            {
                out.print(6);
            }
            out.flush();
            socket.close();
        }
        response.sendRedirect("system");
    }
}

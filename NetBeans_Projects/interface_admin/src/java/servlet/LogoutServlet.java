package servlet;

import java.io.*;
//import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Hugo
 */

@WebServlet(name="LogoutServlet", urlPatterns = "/logout", asyncSupported = true)
public class LogoutServlet extends HttpServlet
{
    private static final Logger LOG = Logger.getLogger(LogoutServlet.class.getName());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getSession(true).invalidate();
        String msg = request.getRemoteUser() + " s'est déconnecté";
        LOG.log(Level.INFO, msg);
        request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }
}

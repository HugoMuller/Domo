package servlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Hugo
 */
//@WebServlet(urlPatterns = "/*")
public class ModeServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getServletContext().getRequestDispatcher("/interface_admin/app/views/partial/header.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if(action != null)
        {
            switch(action)
            {
                case "standard":
                    response.sendRedirect("https://www.google.fr");
                    break;
                case "holiday":
                    break;
                case "alerting":
                    break;
            }
        }
        response.sendRedirect("");
    }
}

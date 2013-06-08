package servlet;

import entities.NavBarEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hugo
 */

@WebServlet(name="HomeServlet", urlPatterns = "/home", asyncSupported = true)
public class HomeServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        NavBarEntity.setCurrentURL(request.getRequestURL().toString());
        request.getServletContext().getRequestDispatcher("/app/views/partials/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    
    }
}

package servlet;

import entities.NavBarEntity;
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
@WebServlet(name="CguServlet", urlPatterns = "/cgu", asyncSupported = true)
public class CguServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        NavBarEntity.setCurrentURL(request.getRequestURL().toString());
        request.getServletContext().getRequestDispatcher("/app/views/partials/cgu.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    
    }
}

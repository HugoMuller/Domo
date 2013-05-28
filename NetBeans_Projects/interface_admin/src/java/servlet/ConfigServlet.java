package servlet;

import entities.ModeEntity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import mode.ModeType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo
 */
@WebServlet(name="ConfigServlet", urlPatterns = "/config", asyncSupported = true)
public class ConfigServlet extends HttpServlet
{
    private static final Logger LOG = Logger.getLogger(ModeServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        LOG.log(Level.SEVERE, request.getRequestURI());
        /*ModeType mode = ModeType.valueOf(request.getParameter("mode"));
        ModeEntity.setMode(mode);
        response.sendRedirect("");*/
        request.getServletContext().getRequestDispatcher("/app/views/partials/config.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // NOTHING
    }
}

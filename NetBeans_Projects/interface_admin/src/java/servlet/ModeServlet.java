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
@WebServlet(name="ModeServlet", urlPatterns = "/mode", asyncSupported = true)
public class ModeServlet extends HttpServlet
{
    private static final Logger LOG = Logger.getLogger(ModeServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        LOG.log(Level.SEVERE, request.getRequestURI());
        ModeType mode = ModeType.valueOf(request.getParameter("mode"));
        ModeEntity.setMode(mode);
        response.sendRedirect("");
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

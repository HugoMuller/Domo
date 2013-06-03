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
        String referer = request.getHeader("Referer");
        String info = "mode " + request.getParameter("mode") + " sélectionné.";
        LOG.log(Level.INFO, info);
        ModeType mode = ModeType.valueOf(request.getParameter("mode"));
        ModeEntity.setMode(mode);
        response.sendRedirect(referer);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // NOTHING
    }
}

package servlet;

import entities.ConfigEntity;
import entities.ModeEntity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mode.ModeType;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author Hugo
 */
@WebServlet(name="ConfigServlet", urlPatterns = "/config", asyncSupported = true)
public class ConfigServlet extends HttpServlet
{
    @Inject
    ConfigEntity configEntity;
    
    private static final Logger LOG = Logger.getLogger(ModeServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //LOG.log(Level.SEVERE, request.getRequestURI());
        /*ModeType mode = ModeType.valueOf(request.getParameter("mode"));
        ModeEntity.setMode(mode);
        response.sendRedirect("");*/
        
        Map<String, String> configStandard = new HashMap(configEntity.getConfigStandard());
        Map<String, String> configHoliday = new HashMap(configEntity.getConfigHoliday());
        Map<String, String> configAlerting = new HashMap(configEntity.getConfigAlerting());
        request.setAttribute("config-standard", configStandard);
        request.setAttribute("config-holiday", configHoliday);
        request.setAttribute("config-alerting", configAlerting);
        
        request.getServletContext().getRequestDispatcher("/app/views/partials/config.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if(action != null)
        {
            if("valid-std".equals(action))
            {
                //handleAddition(request);
            }
            else if("valid-holy".equals(action))
            {
                //handleRemoval(request);
            }
            else if("valid-alert".equals(action))
            {
                
            }
        }
        response.sendRedirect("tasks");
    }
}

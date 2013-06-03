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
import java.util.LinkedList;
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
        LOG.log(Level.INFO, "============================ doPost ===========================");
        String action = request.getParameter("action");
        LOG.log(Level.INFO, action);
        if(action != null)
        {
            if("validate-std".equals(action))
            {
                LOG.log(Level.INFO, "============== action = validate-std ======================");
                handleStandard(request);
            }
            else if("validate-holiday".equals(action))
            {
                LOG.log(Level.INFO, "============== action = validate-holiday ======================");
                handleHoliday(request);
            }
            else if("validate-alerting".equals(action))
            {
                LOG.log(Level.INFO, "============== action = validate-alerting ======================");
                handleAlerting(request);
            }
        }
        response.sendRedirect("config");
    }
    
    private void handleStandard(HttpServletRequest request)
    {
        Map<String, String> newConfigStandard = new HashMap<String, String>();
        String[] heureDebut = request.getParameterValues("std-heure-debut");
        String[] heureFin = request.getParameterValues("std-heure-fin");
        String[] notifications = request.getParameterValues("std-notif");
        String field = "";

        for(String notif : notifications)
        {
        LOG.log(Level.INFO, notif);
        }
        newConfigStandard.put("heureDebut", heureDebut[0]+"h"+heureDebut[1]);
        newConfigStandard.put("heureFin", heureFin[0]+"h"+heureFin[1]);
        
        if(notifications.length == 2)
        {
            newConfigStandard.put("notification", "both");
        }
        else
        {
            newConfigStandard.put("notification", notifications[0]);
        }
        
        for(String notif : notifications)
        {
            if(notif.equals("sms"))
            {
                field = request.getParameterValues("std-sms-field")[0];
            }
            else if(notif.equals("email"))
            {
                field = request.getParameterValues("std-email-field")[0];
            }
            if(field != null)
            {
                newConfigStandard.put(notif, field);
            }
            else
            {
                newConfigStandard.put(notif, "");
            }
        }
        configEntity.setConfigStandard(newConfigStandard);
    }
    
    private void handleHoliday(HttpServletRequest request)
    {
        Map<String, String> newConfigHoliday = new HashMap<String, String>();
        String[] heureDebut = request.getParameterValues("holiday-heure-debut");
        String[] heureFin = request.getParameterValues("holiday-heure-fin");
        String[] notifications = request.getParameterValues("holiday-notif");
        String field = "";

        newConfigHoliday.put("heureDebut", heureDebut[0]+"h"+heureDebut[1]);
        newConfigHoliday.put("heureFin", heureFin[0]+"h"+heureFin[1]);
        
        if(notifications.length == 2)
        {
            newConfigHoliday.put("notification", "both");
        }
        else
        {
            newConfigHoliday.put("notification", notifications[0]);
        }
        
        for(String notif : notifications)
        {
            if(notif.equals("sms"))
            {
                field = request.getParameterValues("holiday-sms-field")[0];
            }
            else if(notif.equals("email"))
            {
                field = request.getParameterValues("holiday-email-field")[0];
            }
            if(field != null)
            {
                newConfigHoliday.put(notif, field);
            }
            else
            {
                newConfigHoliday.put(notif, "");
            }
        }
        configEntity.setConfigHoliday(newConfigHoliday);
    }
    
    private void handleAlerting(HttpServletRequest request)
    {
        Map<String, String> newConfigAlerting = new HashMap<String, String>();
        String[] heureDebut = request.getParameterValues("alerting-heure-debut");
        String[] heureFin = request.getParameterValues("alerting-heure-fin");
        String[] notifications = request.getParameterValues("alerting-notif");
        String field = "";

        newConfigAlerting.put("heureDebut", heureDebut[0]+"h"+heureDebut[1]);
        newConfigAlerting.put("heureFin", heureFin[0]+"h"+heureFin[1]);
        
        if(notifications.length == 2)
        {
            newConfigAlerting.put("notification", "both");
        }
        else
        {
            newConfigAlerting.put("notification", notifications[0]);
        }
        
        for(String notif : notifications)
        {
            if(notif.equals("sms"))
            {
                field = request.getParameterValues("alerting-sms-field")[0];
            }
            else if(notif.equals("email"))
            {
                field = request.getParameterValues("alerting-email-field")[0];
            }
            if(field != null)
            {
                newConfigAlerting.put(notif, field);
            }
            else
            {
                newConfigAlerting.put(notif, "");
            }
        }
        configEntity.setConfigAlerting(newConfigAlerting);
    }
}

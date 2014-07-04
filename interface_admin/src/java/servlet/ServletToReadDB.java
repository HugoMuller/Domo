/*
 * author : YaXi/Elo
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entities.NavBarEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Elo/Yaxi
 */
@WebServlet(urlPatterns = "/Notif")
public class ServletToReadDB extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         NavBarEntity.setCurrentURL(request.getRequestURL().toString());
         request.setAttribute("type", request.getParameter("type"));
         request.getServletContext().getRequestDispatcher("/app/views/notif.jsp").forward(request, response);
     }
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if (type != null) {
            switch (type) {
                case "Chauffage" : 
                    break;
                case "Eclairage" :
                    break;
                case "All": 
                    break;
                default : throw new ServletException("This type of notification is not supported yet");
            }
        }
        response.sendRedirect("Notif");
    }
    
}

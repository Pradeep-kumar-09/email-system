/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package mail;



import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adrianadewunmi
 */
@WebServlet("/MailCtl")
public class MailCtl extends HttpServlet {
    
    public static final String OP_GO="Send";

    public MailCtl() {
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email=request.getParameter("email");
        String subject=request.getParameter("subject");
        String message=request.getParameter("message");
        
        String op=request.getParameter("operation");
        
        if (OP_GO.equalsIgnoreCase(op)) {
            EmailMessage msg = new EmailMessage();
            msg.setTo(email);
            msg.setSubject(subject);
            msg.setMessage(message);
            msg.setMessageType(EmailMessage.HTML_MSG);
            
            EmailUtility.sendMail(msg);
            request.setAttribute("msg", "Mail has been sent successfully!");
        }
        RequestDispatcher rd=request.getRequestDispatcher("index.jps");
        rd.forward(request, response);
    }


}

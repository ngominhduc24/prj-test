/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author ASUS PC
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = { "/changepassword" })
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * 
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * 
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        // delete all cookie message
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("errorpass")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("successpass")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if (cookie.getName().equals("cferror")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        // get email from cookie
        String email = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) {
                email = cookie.getValue();
                break;
            }
        }

        // get data from form
        String oldPassword = request.getParameter("old_Password");
        String newPassword = request.getParameter("new_password");
        String cfNewPassword = request.getParameter("cf_new_password");

        // check old password and new password
        if (newPassword.trim().equals(cfNewPassword.trim())) {
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.checkAccount(email, oldPassword);
            if (account != null) {
                if (accountDAO.changePassword(email, newPassword)) {
                    account.setPassword(newPassword);
                    request.getSession().setAttribute("account", account);

                    // set cookie for password
                    Cookie cookie = new Cookie("password", newPassword);
                    cookie.setMaxAge(60 * 60 * 24);
                    response.addCookie(cookie);

                    // set cookie for success message
                    Cookie cookie1 = new Cookie("successpass", "mess");
                    cookie1.setMaxAge(10);
                    response.addCookie(cookie1);
                    response.sendRedirect("account.jsp");
                }
            } else {
                Cookie cookie1 = new Cookie("errorpass", "mess");
                cookie1.setMaxAge(10);
                response.addCookie(cookie1);
                response.sendRedirect("account.jsp");
            }
        } else {
            Cookie cookie1 = new Cookie("cferror", "mess");
            cookie1.setMaxAge(10);
            response.addCookie(cookie1);
            response.sendRedirect("account.jsp");
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * 
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

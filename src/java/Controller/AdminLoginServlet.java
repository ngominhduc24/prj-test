/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import dal.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import model.Account;
import utils.NumberToEnum.UserRole;

/**
 *
 * @author ASUS PC
 */
@WebServlet(name = "AdminLoginServlet", urlPatterns = { "/admin/login" })
public class AdminLoginServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminLoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminLoginServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("AdminLogin.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        // get parameter
        String email = request.getParameter("email");
        String password = request.getParameter("Password");
        String remember = request.getParameter("remember");

        // delete cookie email and password
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email") || cookie.getName().equals("password")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        // set cookie for email and password
        Cookie cookie1 = new Cookie("email", email);
        cookie1.setMaxAge(60 * 60 * 24);
        Cookie cookie2 = new Cookie("password", password);
        if (remember != null) {
            cookie2.setMaxAge(60 * 60 * 24);
        } else {
            cookie2.setMaxAge(0);
        }
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        // check account
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.checkAccount(email, password);

        // set session
        if (account != null) {
            // if account is admin
            if (account.getRole() == UserRole.ADMIN.getValue()) {
                session.removeAttribute("role");
                session.setAttribute("role", UserRole.ADMIN.getValue());
                session.setAttribute("account", account);
                response.sendRedirect("home");
            }

            // if account is not admin
            if (account.getRole() == UserRole.USER.getValue()) {
                session.setAttribute("loginmessage", "You are not admin");
                response.sendRedirect("login");
            }

        } else {
            session.setAttribute("loginmessage", "Username or password is incorrect");
            response.sendRedirect("login");
        }
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

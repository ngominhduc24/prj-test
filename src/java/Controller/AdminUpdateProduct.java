/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dal.CategoryDAO;
import dal.GiayKhaiSinhDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;
import java.util.List;
import model.GiayKhaiSinh;

/**
 *
 * @author ASUS PC
 */
@WebServlet(name = "AdminUpdateProduct", urlPatterns = {"/admin/updateproduct"})
public class AdminUpdateProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminUpdateProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminUpdateProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
        try {

            int giayKhaiSinhID = Integer.parseInt(request.getParameter("pid"));

            // Sử dụng GiayKhaiSinhDAO để lấy thông tin giấy khai sinh dựa trên ID
            GiayKhaiSinhDAO giayKhaiSinhDAO = new GiayKhaiSinhDAO();
            GiayKhaiSinh giayKhaiSinh = giayKhaiSinhDAO.getGiayKhaiSinhById(giayKhaiSinhID);
            request.setAttribute("giayKhaiSinh", giayKhaiSinh);

            request.getRequestDispatcher("AdminUpdateProduct.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
            response.sendRedirect("home");
        }

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
        GiayKhaiSinhDAO giayKhaiSinhDAO = new GiayKhaiSinhDAO();

        String id = request.getParameter("id");
        String soGiayKhaiSinh = request.getParameter("SoGiayKhaiSinh");
        String hoVaTenCha = request.getParameter("HoVaTenCha");
        String hoVaTenMe = request.getParameter("HoVaTenMe");
        String hoVaTenCon = request.getParameter("HoVaTenCon");
        String gioiTinhCon = request.getParameter("GioiTinhCon");
        String ngaySinhCon = request.getParameter("NgaySinhCon");
        String danTocCon = request.getParameter("DanTocCon");
        String quocTichCon = request.getParameter("QuocTichCon");

        try {

            if (id != null && soGiayKhaiSinh != null && hoVaTenCha != null && hoVaTenMe != null && hoVaTenCon != null && gioiTinhCon != null && danTocCon != null && quocTichCon != null) {
                String formattedDate = "2000-01-15"; // Replace with your desired date format
                GiayKhaiSinh giayKhaiSinh = new GiayKhaiSinh(Integer.parseInt(id), soGiayKhaiSinh, hoVaTenCha, hoVaTenMe, hoVaTenCon, gioiTinhCon, formattedDate, danTocCon, quocTichCon);
                if (giayKhaiSinhDAO.updateGiayKhaiSinh(giayKhaiSinh)) {
                    System.out.println("thanh cong roiii");
                } else {
                    System.out.println("buagg");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        response.sendRedirect("./home");

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

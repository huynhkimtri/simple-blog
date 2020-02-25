/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.controllers;

import fuhcm.lab.trihk.blogging.daos.ArticleDAO;
import fuhcm.lab.trihk.blogging.dtos.ArticleDTO;
import fuhcm.lab.trihk.blogging.dtos.UserDTO;
import fuhcm.lab.trihk.blogging.utilities.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author huynh
 */
@WebServlet(name = "ArticleInsertServlet", urlPatterns = {"/ArticleInsertServlet"})
public class ArticleInsertServlet extends HttpServlet {

    private final String errorPage = "500.html";
    private final String success = "successfully.html";

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
        try (PrintWriter out = response.getWriter()) {
            String path = errorPage;
            try {
                String title = request.getParameter("txtTitle").trim();
                String description = request.getParameter("txtDescription").trim();
                String content = request.getParameter("txtContent").trim();
                content = content.replaceAll("(\r\n|\n)", "<br>");
                HttpSession session = request.getSession();
                UserDTO member = (UserDTO) session.getAttribute("USER");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String createdDate = formatter.format(now);
                if (member != null && member.getEmail() != null) {
                    ArticleDTO dto = new ArticleDTO(title, description, content,
                            createdDate, member.getEmail(), Constants.STATUS_ARTICLE_NEW);
                    ArticleDAO dao = new ArticleDAO();
                    boolean result = dao.insertArticle(dto);
                    if (result) {
                        path = success;
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, e.getMessage());
            } finally {
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
                out.close();
            }
        }
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
        processRequest(request, response);
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

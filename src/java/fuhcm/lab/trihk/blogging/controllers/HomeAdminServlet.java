/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.controllers;

import fuhcm.lab.trihk.blogging.daos.ArticleDAO;
import fuhcm.lab.trihk.blogging.dtos.ArticleMapper;
import fuhcm.lab.trihk.blogging.utilities.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huynh
 */
@WebServlet(name = "HomeAdminServlet", urlPatterns = {"/HomeAdminServlet"})
public class HomeAdminServlet extends HttpServlet {

    private final String adminPage = "admin.jsp";

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
            String path = adminPage;
            ArticleDAO dao = new ArticleDAO();
            int totalResults = dao.getRowsCount(Constants.EMPTY_STRING, Constants.EMPTY_STRING);
            double a = (totalResults / (double) Constants.SIZE);
            int b = totalResults / Constants.SIZE;
            int numberOfPages = 1;
            int pageNum = 1;
            int start = pageNum * Constants.SIZE - Constants.SIZE + 1;
            int stop = pageNum * Constants.SIZE;

            if (a == 0) {
                numberOfPages = 1;
            }
            if (b > a) {
                if (b % a > 0) {
                    numberOfPages = (int) (b - b % a + a);
                }
            }
            if (a >= b) {
                numberOfPages = (int) a + 1;
            }

            request.setAttribute("NUMBER_OF_PAGES", numberOfPages);
            request.setAttribute("PAGE_NUMBER", pageNum);

            dao.getListArticleAndAuthorPagingByTitleAndStatus(Constants.EMPTY_STRING, Constants.EMPTY_STRING, start, stop);
//            dao.getListArticleAndAuthorForAdmin();
            List<ArticleMapper> list = dao.getListArticlesMapper();
            request.setAttribute("LIST_ARTICLES", list);
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huynh
 */
public class MainController extends HttpServlet {

    private final String ACTION_LOGIN = "login";
    private final String ACTION_REGISTER = "register";
    private final String ACTION_LOGOUT = "logout";
    private final String ACTION_HOME = "home";
    private final String ACTION_WRITE = "write-blog";
    private final String ACTION_SUBMIT = "submit";
    private final String homePage = "blog-home.jsp";
    private final String writeBlogPage = "write-blog.jsp";
    private final String homeServlet = "HomeServlet";
    private final String userLoginController = "UserLoginController";
    private final String userRegisterController = "UserRegisterController";
    private final String userLogoutController = "UserLogoutController";
    private final String articleInsertServlet = "ArticleInsertServlet";

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
            String action = request.getParameter("action");
            String url = homePage;
            try {
                if (action == null) {
                    // do nothing -> home page
                } else {
                    switch (action) {
                        case ACTION_HOME:
                            url = homeServlet;
                            break;
                        case ACTION_LOGIN:
                            url = userLoginController;
                            break;
                        case ACTION_REGISTER:
                            url = userRegisterController;
                            break;
                        case ACTION_LOGOUT:
                            url = userLogoutController;
                            break;
                        case ACTION_WRITE:
                            url = writeBlogPage;
                            break;
                        case ACTION_SUBMIT:
                            url = articleInsertServlet;
                            break;
                        default:
                            break;
                    }
                }
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
                requestDispatcher.forward(request, response);
            } catch (IOException e) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                out.close();
            }
        } catch (ServletException servletException) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, servletException);
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

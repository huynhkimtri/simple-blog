/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuhcm.lab.trihk.blogging.controllers;

import fuhcm.lab.trihk.blogging.daos.UserDAO;
import fuhcm.lab.trihk.blogging.dtos.UserDTO;
import fuhcm.lab.trihk.blogging.utilities.Constants;
import fuhcm.lab.trihk.blogging.utilities.HashCryptUtility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "UserLoginController", urlPatterns = {"/UserLoginController"})
public class UserLoginController extends HttpServlet {

    private final String homePage = "blog-home.jsp";
    private final String adminPage = "admin.jsp";
    private final String incorrectPage = "login.jsp";
    private final String emailInput = "txtEmail";
    private final String pwdInput = "txtPassword";

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
            String path = incorrectPage;
            try {
                String email = request.getParameter(emailInput);
                String password = request.getParameter(pwdInput);
                UserDAO dao = new UserDAO();
                HashCryptUtility cryptUtility = new HashCryptUtility();
                String pwsEncrypt = cryptUtility.encryptSHA256(password);
                UserDTO user = dao.checkLogin(email, pwsEncrypt);
                if (user != null) {
                    if (user.getStatus().equals(Constants.USER_STATUS_ACTIVE)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", user);
                        switch (user.getRole()) {
                            case Constants.USER_ROLE_ADMIN:
                                path = adminPage;
                                break;
                            case Constants.USER_ROLE_MEMBER:
                                path = homePage;
                                break;
                        }
                    } else {
                        request.setAttribute("ERROR", Constants.MSG_INACTIVE);
                        request.setAttribute("LASTED_EMAIL", email);
                    }
                } else {
                    request.setAttribute("ERROR", Constants.MSG_INCORRECT);
                    request.setAttribute("LASTED_EMAIL", email);
                }
            } catch (SQLException e) {
                Logger.getLogger(UserLoginController.class.getName()).log(Level.SEVERE, null, e);
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

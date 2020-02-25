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
@WebServlet(name = "UserRegisterServlet", urlPatterns = {"/UserRegisterServlet"})
public class UserRegisterServlet extends HttpServlet {

    private final String emailInput = "txtEmail";
    private final String firstNameInput = "txtFirstName";
    private final String lastNameInput = "txtLastName";
    private final String passwordInput = "txtPassword";
    private final String confirmPasswordInput = "txtConfirmPassword";
    private final String homeServlet = "HomeServlet";
    private final String registerPage = "register.jsp";

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
            String path = registerPage;
            try {
                String email = request.getParameter(emailInput).trim();
                String firstName = request.getParameter(firstNameInput).trim();
                String lastName = request.getParameter(lastNameInput).trim();
                String password = request.getParameter(passwordInput).trim();
                String confirmPassword = request.getParameter(confirmPasswordInput).trim();

                if (confirmPassword.length() > 0 && password.length() > 0) {

                    if (!confirmPassword.equals(password)) {
                        request.setAttribute("MSG_ERROR", Constants.MSG_MATCHING_PWD);
                    } else {
                        if (password.length() < 8) {
                            request.setAttribute("MSG_ERROR", Constants.MSG_INVALID_PWD);
                        } else {
                            UserDAO dao = new UserDAO();
                            boolean checkExisted = dao.checkExistedEmail(email);
                            if (!checkExisted) {
                                HashCryptUtility cryptUtility = new HashCryptUtility();
                                String pwsEncrypt = cryptUtility.encryptSHA256(password);
                                UserDTO dto = new UserDTO(email, pwsEncrypt, firstName, lastName);

                                boolean result = dao.createUser(dto);
                                if (result) {
                                    HttpSession session = request.getSession();
                                    if ((UserDTO) session.getAttribute("USER") != null) {
                                        session.removeAttribute("USER");
                                    }
                                    path = homeServlet;
                                }
                            } else {
                                request.setAttribute("MSG_ERROR", Constants.MSG_EXISTED_EMAIL);
                            }
                        }
                    }
                } else {
                    request.setAttribute("MSG_ERROR", Constants.MSG_BLANK_PWD);
                }
                if (path.equals(registerPage)) {
                    request.setAttribute("LASTED_EMAIL", email);
                    request.setAttribute("LASTED_FIRST_NAME", firstName);
                    request.setAttribute("LASTED_LAST_NAME", lastName);
                }

            } catch (SQLException e) {
                Logger.getLogger(UserRegisterServlet.class.getName()).log(Level.SEVERE, null, e);
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

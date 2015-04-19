/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.servelts;

import com.openqc.facades.UserFacadeLocal;
import com.openqc.sessionbeans.UserAuthBeanLocal;
import java.io.IOException;
import java.util.Arrays;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 *
 * @author ABCD-user
 */
public class MainServelet extends HttpServlet {

    @EJB
    private UserFacadeLocal userFacade;
    @EJB
    private UserAuthBeanLocal userAuthBean;

    private RequestDispatcher requestDispatcher;
    private HttpSession session;
    private String pageRoute = "/index.jsp";

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
        if (request.getParameter("action") != null && request.getParameter("action").equals("logout")) {
            org.apache.shiro.subject.Subject currentUser = SecurityUtils
                    .getSubject();
            currentUser.logout();
            System.out.println("\n \t *############ LOGOUT success  ");
            request.setAttribute("message","LOGOUT success");
            pageRoute = "/index.jsp";
            
        } else if (request.getParameter("action") != null && request.getParameter("action").equals("register")) {
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            System.out.println("\n \t ========= Username: " + username);
            String password = request.getParameter("password");
            System.out.println("\n \t ========= Username: " + password);
            if (null != userFacade.register(email, username, password)) {
                System.out.println("\n \t ******** user registred successfully :/ ");
                requestDispatcher = getServletContext().getRequestDispatcher(pageRoute);
                requestDispatcher.forward(request, response);
                return;
            }
            System.out.println("\n \t ########## Error registring! :/ ");
        } else {
            System.out.println("\n\n ==== Process req :Before loginAction");

            doLogin(request, response);
            this.session = request.getSession(true);
          
        }
        requestDispatcher = getServletContext().getRequestDispatcher(pageRoute);
        requestDispatcher.forward(request, response);
    }

    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        if (email == null || pwd == null) {
             System.out.println("\n\n ===== Mot de passe ou Login ERROR");
            request.setAttribute("message", "wrong parameters");
        } else {
            System.out.println("\n\n Before trying login");
            boolean b = tryLogin(email, pwd, false);
            if (b) {
                  pageRoute="/app.jsp";
                request.setAttribute(
                        "message",
                        "Login successful. Welcome. Open <a href='#'>Hello</a> to check if you are logged in.");
                System.out.println("\n\n **** Login successful");
            } else {
                request.setAttribute("message",
                        "wrong email/pwd or an error...");
                System.out.println("\n\n ==== ERROR Login");
            }
        }

        // draw JSP
        try {
            request.getRequestDispatcher("/index.jsp").include(
                    request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public boolean tryLogin(String username, String password, Boolean rememberMe) {
        // get the currently executing user:
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            // collect user principals and credentials in a gui specific manner
            // such as username/password html form, X509 certificate, OpenID,
            // etc.
            // We'll use the username/password example here since it is the most
            // common.
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
            // this is all you have to do to support 'remember me' (no config -
            // built in!):
            token.setRememberMe(rememberMe);
            try {
                currentUser.login(token);
                System.out.println("User [" + currentUser.getPrincipal().toString() + "] logged in successfully.");
                // save current username in the session, so we have access to
                // our User model
                currentUser.getSession().setAttribute("username", username);
                return true;
            } catch (UnknownAccountException uae) {
                System.out.println("There is no user with username of "
                        + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("Password for account " + token.getPrincipal()
                        + " was incorrect! \n\t Given: " + Arrays.toString(token.getPassword()));
            } catch (LockedAccountException lae) {

                System.out.println("The account for username " + token.getPrincipal() + " is locked.  "
                        + "Please contact your administrator to unlock it.");
            }
        } else {
            return true; // already logged in
        }

        return false;
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

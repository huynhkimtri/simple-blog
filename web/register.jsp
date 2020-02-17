<%-- 
    Document   : login
    Created on : Jan 14, 2020, 11:29:13 AM
    Author     : huynhkimtri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign in to SimpleBlog · SimpleBlog</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/simpleblog/blog-register.css">
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css">
    </head>
    <body>

        <div class="register-page">
            <div class="auth-form mt-4">
                <div class="auth-form-header">
                    <h1>Sign up to Simple Blog</h1>
                </div>
                <!--<div class="alert alert-danger" role="alert" id="msg-alert" style="display: none"></div>-->
                <c:set value="${requestScope.MSG_ERROR}" var="msg"/>
                <c:if test="${not empty msg}">
                    <div class="alert alert-danger" role="alert">${msg}</div>
                </c:if>
                <div class="auth-form-body mt-3">
                    <form action="MainController" method="post">
                        <c:set value="${requestScope.LASTED_EMAIL}" var="email"/>
                        <c:set value="${requestScope.LASTED_FIRST_NAME}" var="firstName"/>
                        <c:set value="${requestScope.LASTED_LAST_NAME}" var="lastName"/>
                        <div class="row">
                            <div class="col-md-6">
                                <label for="firstName">First Name</label>
                                <input type="text" id="firstName" name="txtFirstName" 
                                       value="<c:if test="${not empty firstName}">${firstName}</c:if>"
                                           class="form-control input-block" required>
                                </div>
                                <div class="col-md-6">
                                    <label for="lastName">Last Name</label>
                                    <input type="text" id="lastName" name="txtLastName" 
                                           value="<c:if test="${not empty lastName}">${lastName}</c:if>"
                                    class="form-control input-block" required>
                            </div>
                        </div>
                        <label for="email">Email address</label>
                        <input type="email" id="email" name="txtEmail" 
                               value="<c:if test="${not empty email}">${email}</c:if>"
                               class="form-control input-block" required>
                        <label for="password">Password</label>
                        <input type="password" id="password" name="txtPassword" class="form-control input-block" required>
                        <label for="confirmPassword">Confirm Password</label>
                        <input type="password" id="confirmPassword" name="txtConfirmPassword" class="form-control input-block" required>
                        <input class="btn btn-primary btn-signin btn-block" type="submit" name="action" value="Sign up" />
                    </form>
                </div>
                <p class="signin-callout mt-3"><a href="login.jsp">I already have a membership.</a></p>
            </div>
        </div>
        <script>
            function checkValidationPasswords() {
                let password = document.getElementById("password").value;
                let confirmPassword = document.getElementById("confirmPassword").value;
                let msg = document.getElementById("msg-alert");

                if (password !== confirmPassword) {
                    msg.innerHTML = "Passwords do not match!";
                    if (msg.style.display === "none") {
                        msg.style.display = "block";
                    }
                    console.log("Passwords do not match!");
                    return false;
                } else {
                    if (password.length === 0 || confirmPassword.length === 0) {
                        console.log("Passwords can not blank!");
                    } else {
                        console.log("Passwords are matching!");
                        return true;
                    }
                }
            }
        </script>
    </body>
</html>

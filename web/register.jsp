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
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Theme style -->
        <link rel="stylesheet" href="css/simpleblog/blog-register.css">
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css">
    </head>
    <body>
        <div class="register-page">
            <div class="auth-form mt-4">
                <div class="auth-form-header">
                    <h1>Sign up to Simple Blog</h1>
                </div>
                <div class="auth-form-body mt-3">
                    <form action="MainController" method="post">
                        <label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="txtFirstName" class="form-control input-block" required autofocus>
                        <label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="txtLastName" class="form-control input-block" required>
                        <label for="email">Email address</label>
                        <input type="email" id="email" name="txtEmail" class="form-control input-block" required>
                        <label for="password">Password</label>
                        <input type="password" id="password" name="txtPassword" class="form-control input-block" required>
                        <label for="cfPassword">Confirm Password</label>
                        <input type="password" id="cfPassword" name="txtPassword" class="form-control input-block" required>
                        <input class="btn btn-primary btn-signin btn-block" type="submit" name="action" value="Sign up" />
                    </form>
                </div>
                <p class="signin-callout mt-3"><a href="login.jsp">I already have a membership.</a></p>
            </div>
        </div>
    </body>
</html>
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
        <link rel="stylesheet" href="css/simpleblog/blog-login.css">
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css">
    </head>
    <body>
        <div class="signin-page">
            <div class="auth-form mt-4">
                <div class="auth-form-header">
                    <h1>Sign in to Simple Blog</h1>
                </div>
                <c:set value="${requestScope.ERROR}" var="error"/>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">${error}</div>
                </c:if>
                <div class="auth-form-body mt-3">
                    <form action="MainController" method="post">
                        <label for="email">Email address</label>
                        <input type="email" id="email" name="txtEmail" class="form-control input-block" required autofocus>
                        <label for="password">Password<a class="label-link" href="/password_reset">Forgot password?</a></label>
                        <input type="password" id="password" name="txtPassword" class="form-control input-block" required>
                        <input class="btn btn-primary btn-signin btn-block" type="submit" name="action" value="Sign in" />
                    </form>
                </div>
                <p class="create-account-callout mt-3">New to SimleBlog? <a href="register.jsp">Create an account.</a></p>
            </div>
        </div>
    </body>
</html>

<%-- 
    Document   : home.jsp
    Created on : Jan 14, 2020, 4:16:34 PM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Write an article · SimpleBlog</title>
        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/simpleblog/blog-write.css" rel="stylesheet">
        <!-- Font Awesome Icons -->
        <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
    </head>
    <body>
        <c:set value="${sessionScope.USER}" var="user" />
        <c:if test="${not empty user}">
            <c:choose>
                <c:when test="${user.role eq 'admin'}">
                    <c:redirect url="admin.jsp"/>
                </c:when>
                <c:when test="${user.role eq 'member'}">
                    <c:set value="${user.firstName}" var="firstName"/>
                    <c:set value="${user.lastName}" var="lastName"/>
                </c:when>
            </c:choose>
        </c:if>
        <c:if test="${empty user}">
            <c:redirect url="MainController?action=login"/>
        </c:if>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="">Simple Blog</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="MainController?action=home">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <c:choose>
                            <c:when test="${not empty firstName and not empty lastName}">
                                <li class="nav-item active">
                                    <div class="dropdown">
                                        <a class="nav-link dropdown-toggle" 
                                           href="#" role="button" id="dropdownMenuLink" 
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            ${firstName}&nbsp;${lastName}
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <a class="dropdown-item" href="#">Profile</a>
                                            <div class="dropdown-divider"></div>
                                            <c:set value="MainController?action=Sign out" var="urlSignOut" />
                                            <a class="dropdown-item" href="${urlSignOut}" >Sign out</a>
                                        </div>
                                    </div> 
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a class="nav-link" href="login.jsp">Sign in</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="register.jsp">Sign up</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="card my-4">
                        <h5 class="card-header">Write an article</h5>
                        <div class="card-body">
                            <form action="MainController" method="post">
                                <div class="form-group">
                                    <p style="margin-bottom: 0; font-weight: bold" class="mb-2">Title:</p>
                                    <textarea class="form-control" name="txtTitle" rows="2" required autofocus></textarea>
                                </div>
                                <div class="form-group">
                                    <p style="margin-bottom: 0; font-weight: bold" class="mb-2">Description:</p>
                                    <textarea class="form-control" name="txtDescription" rows="2" required></textarea>
                                </div>
                                <div class="form-group">
                                    <p style="margin-bottom: 0; font-weight: bold" class="mb-2">Content:</p>
                                    <textarea class="form-control" name="txtContent" rows="10" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary" name="action" value="submit">Submit</button>
                                <button type="reset" class="btn btn-outline-secondary">Reset</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <!-- Footer -->
        <footer class="py-5 bg-dark">
            <div class="container">
                <p class="m-0 text-center text-white">Copyright 2019 &copy; TriHK-SE63285</p>
            </div>
            <!-- /.container -->
        </footer>

        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    </body>
</html>

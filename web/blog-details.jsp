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
        <title>Home · SimpleBlog</title>
        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="css/simpleblog/blog-home.css" rel="stylesheet">
        <!-- Font Awesome Icons -->
        <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
    </head>
    <body>
        <c:set value="${sessionScope.USER}" var="user" />
        <c:if test="${not empty user}">
            <c:set value="${user.firstName}" var="firstName"/>
            <c:set value="${user.lastName}" var="lastName"/>
            <c:set value="${user.email}" var="email"/>
        </c:if>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="#">Simple Blog</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="MainController?action=home"><i class="fas fa-home"></i>&nbsp;Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <c:choose>
                            <c:when test="${not empty firstName and not empty lastName}">
                                <li class="nav-item">
                                    <div class="dropdown">
                                        <a class="nav-link dropdown-toggle" 
                                           href="#" role="button" id="dropdownMenuLink" 
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            ${firstName}&nbsp;${lastName}
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <c:set value="MainController?action=logout" var="url"/>
                                            <a class="dropdown-item" href="${url}" >Sign out</a>
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
                <!-- Blog Entries Column -->
                <div class="col-md-12">
                    <c:set value="${sessionScope.preQuery}" var="query"/>
                    <c:if test="${not empty query}"><p style="margin-top: 10px"><a href="MainController?${query}"><i class="fas fa-arrow-left"></i> Back </a></p></c:if>
                    <c:if test="${empty query}"><p style="margin-top: 10px"><a href="MainController"><i class="fas fa-arrow-left"></i> Back </a></p></c:if>
                    <c:set value="${requestScope.ARTICLE_MAPPER}" var="mapper"/>
                    <c:if test="${not empty mapper}">
                        <!-- Title -->
                        <h1 class="mt-1">${mapper.title}</h1>
                        <!-- Author -->
                        <p class="lead">by <a href="MainController?action=profile&email=${mapper.authorEmail}"><strong>${mapper.authorFirstName}&nbsp;${mapper.authorLastName}</strong></a></p>
                        <hr>
                        <c:choose>
                            <c:when test="${user.role eq 'admin'}">
                                <c:if test="${mapper.publishedDate.length() == 0}">
                                    <!-- Date/Time -->
                                    <p>Posted at ${mapper.createdDate}</p>
                                </c:if>
                                <c:if test="${mapper.publishedDate.length() > 0}">
                                    <!-- Date/Time -->
                                    <p>Posted at ${mapper.createdDate}</p>
                                    <p>Published at ${mapper.publishedDate}</p>
                                </c:if>
                                <c:if test="${mapper.status eq 'new'}">
                                    <p>Status <span class="badge badge-warning">${mapper.status}</span></p>
                                    </c:if>
                                    <c:if test="${mapper.status eq 'active'}">
                                    <p>Status <span class="badge badge-success">${mapper.status}</span></p>
                                    </c:if>
                                    <c:if test="${mapper.status eq 'delete'}">
                                    <p>Status <span class="badge badge-danger">${mapper.status}</span></p>
                                    </c:if>
                                </c:when>
                                <c:when test="${user.role eq 'member'}">
                                <!-- Date/Time -->
                                <p>Published at ${mapper.publishedDate}</p>
                            </c:when>
                        </c:choose>

                        <hr>
                        <!-- Post Content -->
                        <p>${mapper.description}</p>
                        <hr>
                        <p>${mapper.content}</p>
                        <hr>

                        <!-- Comments Form -->
                        <c:if test="${not empty user and user.role eq 'member'}">
                            <div class="card my-4">
                                <h5 class="card-header">Leave a Comment:</h5>
                                <div class="card-body">
                                    <form action="MainController" method="get">
                                        <div class="form-group">
                                            <textarea class="form-control" name="comment" rows="3"></textarea>
                                        </div>
                                        <input type="hidden" value="${email}" name="user"/>
                                        <input type="hidden" value="${param.id}" name="atcId"/>
                                        <button type="submit" class="btn btn-primary" name="action" value="comment">Submit</button>
                                    </form>
                                </div>
                            </div>
                        </c:if>
                        <c:set value="${requestScope.COMMENTS}" var="comments" />
                        <c:if test="${not empty comments}">
                            <c:forEach items="${comments}" var="comment">
                                <div class="media mb-4">
                                    <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
                                    <div class="media-body">
                                        <h5 class="mt-0">${comment.userFirstName}&nbsp;${comment.userLastName} <small style="color: #999999">${comment.date}</small></h5>
                                            ${comment.comment}
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>

                    </c:if>
                </div>
            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <!-- Footer -->
        <%@include file="sgmFooter.jspf" %>

        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script>
            function checkSearchValue() {
                let search = document.getElementById("searchValue").value.trim();
                if (search.length === 0) {
                    return false;
                } else {
                    return true;
                }
            }
        </script>
    </body>
</html>

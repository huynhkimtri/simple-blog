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
                            <a class="nav-link" href="MainController?action=home"><i class="fas fa-home"></i> Home
                            </a>
                        </li>
                        <c:if test="${user.role eq 'member'}">
                            <li class="nav-item">
                                <a class="nav-link" href="MainController?action=write-blog"><i class="fas fa-edit"></i> Write
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${user.role eq 'admin'}">
                            <li class="nav-item">
                                <a class="nav-link" href="MainController?action=admin"><i class="fas fa-tasks"></i> Management
                                </a>
                            </li>
                        </c:if>
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
                <div class="col-md-8">
                    <!-- List Articles --> 
                    <c:set value="${requestScope.LIST_ARTICLES}" var="listArticles"/>
                    <c:if test="${not empty listArticles}">
                        <c:forEach var="article" items="${listArticles}">
                            <div class="card mb-4 mt-4">
                                <div class="card-body">
                                    <h2 class="card-title">${article.title}</h2>
                                    <p class="card-text">${article.description}</p>

                                    <a href="MainController?action=view&id=${article.id}" class="btn btn-outline-primary">
                                        <c:set value="${pageContext.request.queryString}" var="preQuery" scope="session"/>Read More &rarr;</a>
                                </div>
                                <div class="card-footer text-muted">
                                    Published at ${article.publishedDate} by
                                    <a href="#"><strong>${article.authorFirstName}&nbsp;${article.authorLastName}</strong></a>
                                </div>
                            </div>
                        </c:forEach>
                        <!-- Pagination -->
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center mb-4">
                                <c:set value="${requestScope.PAGE_NUMBER}" var="pageNummber" />
                                <c:set value="${requestScope.NUMBER_OF_PAGES}" var="numberOfPages" />
                                <!--For displaying Older link-->
                                <c:choose>
                                    <c:when test="${pageNummber gt 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="MainController?action=search&page=${pageNummber - 1}&cbxStatus=&role=member&txtSearchValue=" tabindex="-1">&larr; Older</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" tabindex="-1">&larr; Older</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <!--For displaying Newer link--> 
                                <c:choose>
                                    <c:when test="${pageNummber lt numberOfPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="MainController?action=search&page=${pageNummber + 1}&cbxStatus=&role=member&txtSearchValue=">Newer &rarr;</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" tabindex="-1">Newer &rarr;</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </nav>
                    </c:if>
                    <!-- /.List Articles -->
                    <!-- List Search Results -->
                    <c:set value="${requestScope.LIST_SEARCH_RESULTS}" var="listSearchResults"/>
                    <c:if test="${not empty listSearchResults}">
                        <c:set value="${requestScope.SEARCH_VALUE}" var="searchValue"/>
                        <c:if test="${not empty searchValue}">
                            <h5 class="my-4">Results for keyword search: <i style="color: #0077f2">${searchValue}</i></h5>
                            </c:if>
                            <c:forEach var="result" items="${listSearchResults}">
                            <div class="card mb-4 mt-4">
                                <div class="card-body">
                                    <h2 class="card-title">${result.title}</h2>
                                    <p class="card-text">${result.description}</p>
                                    <c:set value="${pageContext.request.queryString}" var="preQuery" scope="session"/>
                                    <a href="MainController?action=view&id=${result.id}" class="btn btn-outline-primary">Read More &rarr;</a>
                                </div>
                                <div class="card-footer text-muted">
                                    Published ${result.publishedDate} by
                                    <a href="#"><strong>${result.authorFirstName}&nbsp;${result.authorLastName}</strong></a>
                                </div>
                            </div>
                        </c:forEach>
                        <!-- Pagination -->
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center mb-4">
                                <c:set value="${requestScope.PAGE_NUMBER}" var="pageNummber" />
                                <c:set value="${requestScope.NUMBER_OF_PAGES}" var="numberOfPages" />
                                <!--For displaying Older link-->
                                <c:choose>
                                    <c:when test="${pageNummber gt 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="MainController?action=search&page=${pageNummber - 1}&cbxStatus=${param.cbxStatus}&role=member&txtSearchValue=${param.txtSearchValue}" tabindex="-1">&larr; Older</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" tabindex="-1">&larr; Older</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <!--For displaying Newer link--> 
                                <c:choose>
                                    <c:when test="${pageNummber lt numberOfPages}">
                                        <li class="page-item">
                                            <a class="page-link" href="MainController?action=search&page=${pageNummber + 1}&cbxStatus=${param.cbxStatus}&role=member&txtSearchValue=${param.txtSearchValue}">Newer &rarr;</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" tabindex="-1">Newer &rarr;</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </nav>
                    </c:if>
                    <c:if test="${empty listSearchResults}">
                        <c:set value="${requestScope.SEARCH_VALUE}" var="searchValue"/>
                        <c:if test="${not empty searchValue}">
                            <h5 class="my-4">Sorry! We could not find any results for keyword search <i style="color: #0077f2">${searchValue}</i></h5>
                            </c:if>
                        </c:if>
                    <!-- /.List Search Results -->
                </div>
                <!-- /.Blog Entries Column -->

                <!-- Sidebar Widgets Column -->
                <div class="col-md-4">
                    <!-- Search Widget -->
                    <div class="card my-4 search-card">
                        <h5 class="card-header">Search by article title</h5>
                        <div class="card-body">
                            <form action="MainController" method="get">
                                <div class="input-group">
                                    <c:set value="${requestScope.SEARCH_VALUE}" var="searchValue"/>
                                    <input class="form-control" id="searchValue" type="text" 
                                           <c:if test="${not empty searchValue}">value="${searchValue}"</c:if>
                                               placeholder="Title or keyword..." aria-label="Search"
                                               name="txtSearchValue" required>
                                           <input type="hidden" name="role" value="member">
                                           <div class="input-group-append">
                                               <button class="btn btn-primary" name="action" 
                                                       value="search" onclick="return checkSearchValue()" type="submit">
                                                   <i class="fas fa-search"></i></button>
                                           </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <!-- /.Search Widget -->
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

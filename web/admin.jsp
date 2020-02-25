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
        <title>Blog Management · SimpleBlog</title>
        <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="css/simpleblog/blog-admin.css">
    </head>
    <body>
        <c:set value="${sessionScope.USER}" var="user"/>
        <c:if test="${empty user}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${not empty user and user.role eq 'admin'}">
            <c:set value="${user.firstName}" var="firstNameAdmin"/>
            <c:set value="${user.lastName}" var="lastNameAdmin"/>
        </c:if>
        <c:if test="${user.role != 'admin'}">
            <c:redirect url="MainController?action=home"/>
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
                        <li class="nav-item">
                            <a class="nav-link" href="MainController?action=admin"><i class="fas fa-tasks"></i> Management
                            </a>
                        </li>
                        <c:choose>
                            <c:when test="${not empty firstNameAdmin and not empty lastNameAdmin}">
                                <li class="nav-item active">
                                    <div class="dropdown">
                                        <a class="nav-link dropdown-toggle" 
                                           href="#" role="button" id="dropdownMenuLink" 
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            ${firstNameAdmin}&nbsp;${lastNameAdmin}
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <c:set value="MainController?action=logout" var="url" />
                                            <a class="dropdown-item" href="${url}">Sign out</a>
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
        <!-- Page content -->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="card my-4">
                        <h5 class="card-header">Search</h5>
                        <div class="card-body">
                            <form action="MainController" method="get">
                                <div class="row mb-2">
                                    <div class="col-md-6">
                                        <p style="margin-bottom: 0; font-weight: bold" class="mb-1">Status:</p>
                                        <div class="row col-md-12 mt-2">
                                            <c:set value="${requestScope.STATUS_VALUE}" var="statusValue"/>
                                            <c:if test="${not empty statusValue}">
                                                <div class="custom-control custom-radio col-md-3">
                                                    <input type="radio" name="cbxStatus" value="all" class="custom-control-input" id="cbxAll" checked="true">
                                                    <label class="custom-control-label" for="cbxAll">All</label>
                                                </div>
                                                <div class="custom-control custom-radio col-md-3">
                                                    <input type="radio" name="cbxStatus" value="new" class="custom-control-input" id="cbxNew"
                                                           <c:if test="${statusValue eq 'new'}">checked="true"</c:if>/>
                                                           <label class="custom-control-label" for="cbxNew">New</label>
                                                    </div>
                                                    <div class="custom-control custom-radio col-md-3">
                                                        <input type="radio" name="cbxStatus" value="active" class="custom-control-input" id="cbxActive"
                                                        <c:if test="${statusValue eq 'active'}">checked="true"</c:if>/>
                                                        <label class="custom-control-label" for="cbxActive">Active</label>
                                                    </div>
                                                    <div class="custom-control custom-radio col-md-3">
                                                        <input type="radio" name="cbxStatus" value="delete" class="custom-control-input" id="cbxDelete"
                                                        <c:if test="${statusValue eq 'delete'}">checked="true"</c:if>/>
                                                        <label class="custom-control-label" for="cbxDelete">Delete</label>
                                                    </div>
                                            </c:if>

                                            <c:if test="${empty statusValue}">
                                                <div class="custom-control custom-radio col-md-3">
                                                    <input type="radio" name="cbxStatus" value="all" class="custom-control-input" id="cbxAll" checked="true">
                                                    <label class="custom-control-label" for="cbxAll">All</label>
                                                </div>
                                                <div class="custom-control custom-radio col-md-3">
                                                    <input type="radio" name="cbxStatus" value="new" class="custom-control-input" id="cbxNew">
                                                    <label class="custom-control-label" for="cbxNew">New</label>
                                                </div>
                                                <div class="custom-control custom-radio col-md-3">
                                                    <input type="radio" name="cbxStatus" value="active" class="custom-control-input" id="cbxActive">
                                                    <label class="custom-control-label" for="cbxActive">Active</label>
                                                </div>
                                                <div class="custom-control custom-radio col-md-3">
                                                    <input type="radio" name="cbxStatus" value="delete" class="custom-control-input" id="cbxDelete">
                                                    <label class="custom-control-label" for="cbxDelete">Delete</label>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <p style="margin-bottom: 0; font-weight: bold" class="mb-1">Article name:</p>
                                        <div class="row col-md-12 input-search">
                                            <input type="hidden" value="admin" name="role"/>
                                            <c:set value="${requestScope.SEARCH_VALUE}" var="searchValue"/>
                                            <input class="form-control col-md-9" type="text" name="txtSearchValue" placeholder="Title or keyword..." 
                                                   <c:if test="${not empty searchValue}">value="${searchValue}"</c:if>
                                                       aria-label="Search" aria-describedby="basic-addon2" />
                                                   <input type="hidden" value="1" name="page"/>
                                                   <button class="btn btn-primary btn-search" type="submit" value="search" name="action">Search</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <h5 class="card-header">List of Articles</h5>
                            <div class="card-body">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                            <th style="width: 15px">#</th>
                                            <th style="width: 25%">Title</th>
                                            <th style="width: 170px">DateTime</th>
                                            <th>Author</th>
                                            <th>Status</th>
                                            <th>Descriptions</th>
                                            <th style="width: 270px">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:set value="${requestScope.LIST_ARTICLES}" var="listArticles"/>
                                    <c:if test="${not empty listArticles}">
                                        <c:forEach var="article" items="${listArticles}" varStatus="counter">
                                            <tr>
                                                <th>#<input type="hidden" id="article-${article.id}" value="${article.id}"/></th>
                                                <td>${article.title}</td>
                                                <td>${article.createdDate}</td>
                                                <td>${article.authorFirstName}&nbsp;${article.authorLastName}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${article.status eq 'new'}">
                                                            <span class="badge badge-warning">${article.status}</span>
                                                        </c:when>
                                                        <c:when test="${article.status eq 'active'}">
                                                            <span class="badge badge-success">${article.status}</span>
                                                        </c:when>
                                                        <c:when test="${article.status eq 'delete'}">
                                                            <span class="badge badge-danger">${article.status}</span>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td>${article.description}</td>
                                                <td>
                                                    <a class="btn btn-primary btn-sm" href="MainController?action=view&id=${article.id}">
                                                        <c:set value="${pageContext.request.queryString}" var="preQuery" scope="session"/>
                                                        <i class="fas fa-folder">
                                                        </i>
                                                        View
                                                    </a>
                                                    <c:choose>
                                                        <c:when test="${article.status eq 'new'}">
                                                            <!--data-toggle="modal" data-target="#modelApprove"-->
                                                            <a class="btn btn-success btn-sm" href="MainController?action=active&id=${article.id}">
                                                                <i class="fas fa-pencil-alt">
                                                                </i>
                                                                Active
                                                            </a>
                                                            <a class="btn btn-danger btn-sm" href="MainController?action=delete&id=${article.id}">
                                                                <i class="fas fa-trash">
                                                                </i>
                                                                Delete
                                                            </a>
                                                        </c:when>
                                                        <c:when test="${article.status eq 'active'}">
                                                            <a class="btn btn-success btn-sm disabled" href="#">
                                                                <i class="fas fa-pencil-alt">
                                                                </i>
                                                                Active
                                                            </a>
                                                            <a class="btn btn-danger btn-sm" href="MainController?action=delete&id=${article.id}" >
                                                                <i class="fas fa-trash"></i> Delete
                                                            </a>
                                                        </c:when>
                                                        <c:when test="${article.status eq 'delete'}">
                                                            <a class="btn btn-success btn-sm" href="MainController?action=active&id=${article.id}">
                                                                <i class="fas fa-pencil-alt">
                                                                </i>
                                                                Active
                                                            </a>
                                                            <a class="btn btn-danger btn-sm disabled" href="#">
                                                                <i class="fas fa-trash"></i> Delete
                                                            </a>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    <c:set value="${requestScope.LIST_SEARCH_RESULTS}" var="listResults"/>
                                    <c:if test="${not empty listResults}">
                                        <c:forEach var="article" items="${listResults}" varStatus="counter">
                                            <tr>
                                                <th>#<input type="hidden" id="article-${article.id}" value="${article.id}"/></th>
                                                <td>${article.title}</td>
                                                <td>${article.createdDate}</td>
                                                <td>${article.authorFirstName}&nbsp;${article.authorLastName}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${article.status eq 'new'}">
                                                            <span class="badge badge-warning">${article.status}</span>
                                                        </c:when>
                                                        <c:when test="${article.status eq 'active'}">
                                                            <span class="badge badge-success">${article.status}</span>
                                                        </c:when>
                                                        <c:when test="${article.status eq 'delete'}">
                                                            <span class="badge badge-danger">${article.status}</span>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                                <td>${article.description}</td>
                                                <td>
                                                    <a class="btn btn-primary btn-sm" href="MainController?action=view&id=${article.id}">
                                                        <c:set value="${pageContext.request.queryString}" var="preQuery" scope="session"/>
                                                        <i class="fas fa-folder">
                                                        </i>
                                                        View
                                                    </a>
                                                    <c:choose>
                                                        <c:when test="${article.status eq 'new'}">
                                                            <!--data-toggle="modal" data-target="#modelApprove"-->
                                                            <a class="btn btn-success btn-sm" href="MainController?action=active&id=${article.id}">
                                                                <i class="fas fa-pencil-alt">
                                                                </i>
                                                                Active
                                                            </a>
                                                            <a class="btn btn-danger btn-sm" href="MainController?action=delete&id=${article.id}">
                                                                <i class="fas fa-trash">
                                                                </i>
                                                                Delete
                                                            </a>
                                                        </c:when>
                                                        <c:when test="${article.status eq 'active'}">
                                                            <a class="btn btn-success btn-sm disabled" href="#">
                                                                <i class="fas fa-pencil-alt">
                                                                </i>
                                                                Active
                                                            </a>
                                                            <a class="btn btn-danger btn-sm" href="MainController?action=delete&id=${article.id}" >
                                                                <i class="fas fa-trash"></i> Delete
                                                            </a>
                                                        </c:when>
                                                        <c:when test="${article.status eq 'delete'}">
                                                            <a class="btn btn-success btn-sm" href="MainController?action=active&id=${article.id}">
                                                                <i class="fas fa-pencil-alt">
                                                                </i>
                                                                Active
                                                            </a>
                                                            <a class="btn btn-danger btn-sm disabled" href="#">
                                                                <i class="fas fa-trash"></i> Delete
                                                            </a>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                        <c:choose>
                            <c:when test="${empty listArticles and empty listResults}">
                                <h4 class="my-4 mb-5 text-center">Sorry! We could not find any results for keyword search <i style="color: #0077f2">${searchValue}</i></h4>
                                </c:when>
                                <c:otherwise>
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-center">
                                        <c:set value="${requestScope.PAGE_NUMBER}" var="pageNummber" />
                                        <c:set value="${requestScope.NUMBER_OF_PAGES}" var="numberOfPages" />
                                        <%--For displaying Previous link --%>
                                        <c:if test="${pageNummber gt 1}">
                                            <li class="page-item">
                                                <a class="page-link" href="MainController?action=search&page=${pageNummber - 1}&cbxStatus=${param.cbxStatus}&role=admin&txtSearchValue=${param.txtSearchValue}" tabindex="-1">Previous</a>
                                            </li>
                                        </c:if>

                                        <c:forEach begin="1" end="${numberOfPages}" var="i">
                                            <c:set var="currentPage" value="${i}"></c:set>
                                            <c:choose>
                                                <c:when test="${i != pageNummber}">
                                                    <c:set value="MainController?action=search&page=${i}&cbxStatus=${param.cbxStatus}&role=admin&txtSearchValue=${param.txtSearchValue}" var="url"/>
                                                    <li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <li class="page-item active"><a class="page-link" href="${url}">${i}</a></li>
                                                    </c:otherwise>        
                                                </c:choose>       
                                            </c:forEach>  
                                            <%--For displaying Next link --%>
                                            <c:if test="${pageNummber lt numberOfPages}">
                                            <li class="page-item">
                                                <a class="page-link" href="MainController?action=search&page=${pageNummber + 1}&cbxStatus=${param.cbxStatus}&role=admin&txtSearchValue=${param.txtSearchValue}">Next</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="modelApprove" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">Confirm Approve</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Are you sure you want to approve this article to publish?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" name="action" value="approve" class="btn btn-success">Yes, Approve!</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modelDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">Confirm Delete</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="modal-body">
                        Are you sure you want to delete this article? You won't able to revert this!
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-danger">Yes, Delete!</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- ./wrapper -->
        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <script type="text/javascript">
            $(document).on("click", ".open-modal", function () {
                var x = new Date();
                var myHeading = "<p>I Am Added Dynamically </p>";
                $("#modal-body").html(myHeading + x);
//                $('#modelDelete').modal('show');
            });

        </script>
    </body>
</html>

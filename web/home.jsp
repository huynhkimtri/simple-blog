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
        <title>Home | Simple Blog</title>
        <!-- Font Awesome Icons -->
        <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="dist/css/adminlte.min.css">
        <!-- Google Font: Source Sans Pro -->
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
    </head>
    <body >
        <div class="wrapper">
            <!-- Navigation Bar -->
            <nav class="main-header navbar navbar-expand-md navbar-light navbar-white">
                <div class="container">

                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a href="" class="nav-link active">Home</a>
                        </li>
                    </ul>

                    <div class="collapse navbar-collapse order-3" id="navbarCollapse">
                        <!-- Search Form -->
                        <form class="form-inline ml-0" action="MainController" method="post">
                            <div class="input-group input-group-sm">
                                <input class="form-control form-control-navbar" style="width: 200px" type="search" placeholder="Search by title" aria-label="Search">
                                <div class="input-group-append">
                                    <button class="btn btn-navbar" type="submit" name="btnAction">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <c:set var="username" value="${sessionScope.USER_NAME}"/>
                    <!-- User Logged -->
                    <c:if test="${not empty username}">
                        <ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
                            <li class="nav-item">
                                <a href="#">${username}</a>
                            </li>
                            <li class="nav-item">
                                <form action="MainController" method="post">
                                    <button type="submit" name="btnAction" value="Logout" class="btn btn-outline-primary">Sign out</button>
                                </form>
                            </li>
                        </ul>
                    </c:if>
                    <!-- Login and Logout -->
                    <c:if test="${empty username}">
                        <ul class="order-1 order-md-3 navbar-nav navbar-no-expand ml-auto">
                            <li class="nav-item">
                                <a href="login.html" class="btn btn-primary">Sign in</a>
                            </li>
                            <li class="nav-item">
                                <a href="register.html" class="btn">Sign up</a>
                            </li>
                        </ul>
                    </c:if>

                </div>
            </nav>
            <!-- /.navbar -->

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <div class="content-header">
                    <div class="container">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1 class="m-0 text-dark">Simple Blog</h1>
                            </div><!-- /.col -->
                        </div><!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content-header -->

                <!-- Main content -->
                <div class="content">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="card card-primary card-outline">
                                    <div class="card-body">
                                        <h4 class="card-title" style="font-size: 1.5rem;">Hấp dẫn giải bóng đá Xuấn ấm áp 2020 của sinh viên Đại học FPT</h4>
                                        <p class="card-text"><i>10/01/2020</i> - <i>Huynh Kim Tri</i></p>
                                        <p class="card-text">Giải bóng đá Xuân ấm áp ến, xuân về. Toàn bộ số tiền lệ phí tham gia và tài trợ sẽ được dùng để tặng quà cho những người vô gia cư tại TP. Hồ Chí Minh.</p>
                                        <a href="#" class="btn btn-primary">Xem thêm</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--                        <div class="row">
                                                    <div class="col-lg-4">
                                                        <div class="card card-primary card-outline">
                                                            <div class="card-header">
                                                                <h4 class="card-title m-0" style="font-size: 1.5rem">Hấp dẫn giải bóng đá Xuấn ấm áp 2020 của sinh viên Đại học FPT</h4>
                                                            </div>
                                                            <div class="card-body">
                                                                <p class="card-text">Giải bóng đá Xuân ấm áp 2020 là sân chơi cho các bạn sinh viên Đại học FPT đam mê bóng đá trong dịp tết đến, xuân về. Toàn bộ số tiền lệ phí tham gia và tài trợ sẽ được dùng để tặng quà cho những người vô gia cư tại TP. Hồ Chí Minh.</p>
                                                                <a href="#" class="btn btn-primary">Xem thêm</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     /.col-md-6 
                                                    <div class="col-lg-4">
                                                        <div class="card card-primary card-outline">
                                                            <div class="card-header">
                                                                <h4 class="card-title m-0" style="font-size: 1.5rem">Hấp dẫn giải bóng đá Xuấn ấm áp 2020 của sinh viên Đại học FPT</h4>
                                                            </div>
                                                            <div class="card-body">
                                                                <p class="card-text">Giải bóng đá Xuân ấm áp 2020 là sân chơi cho các bạn sinh viên Đại học FPT đam mê bóng đá trong dịp tết đến, xuân về. Toàn bộ số tiền lệ phí tham gia và tài trợ sẽ được dùng để tặng quà cho những người vô gia cư tại TP. Hồ Chí Minh.</p>
                                                                <a href="#" class="btn btn-primary">Xem thêm</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     /.col-md-6 
                                                    <div class="col-lg-4">
                                                        <div class="card card-primary card-outline">
                                                            <div class="card-header">
                                                                <h4 class="card-title m-0" style="font-size: 1.5rem">Hấp dẫn giải bóng đá Xuấn ấm áp 2020 của sinh viên Đại học FPT</h4>
                                                            </div>
                                                            <div class="card-body">n về. Toàn bộ số tiền lệ phí tham gia và tài trợ sẽ được dùng để tặng quà cho những người vô gia cư tại TP. Hồ Chí Minh.</p>
                                                                <a href="#" class="btn btn-primary">Xem thêm</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     /.col-md-6 
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-4">
                                                        <div class="card card-primary card-outline">
                                                            <div class="card-header">
                                                                <h4 class="card-title m-0" style="font-size: 1.5rem">Hấp dẫn giải bóng đá Xuấn ấm áp 2020 của sinh viên Đại học FPT</h4>
                                                            </div>
                                                            <div class="card-body">
                                                                <p class="card-text">Giải bóng đá Xuân ấm áp 2020 là sân chơiê bóng đá trong dịp tết đến, xuân về. Toàn bộ số tiền lệ phí tham gia và tài trợ sẽ được dùng để tặng quà cho những người vô gia cư tại TP. Hồ Chí Minh.</p>
                                                                <a href="#" class="btn btn-primary">Xem thêm</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     /.col-md-6 
                                                    <div class="col-lg-4">
                                                        <div class="card card-primary card-outline">
                                                            <div class="card-header">
                                                                <h4 class="card-title m-0" style="font-size: 1.5rem">Hấp dẫn giải bóng đá Xuấn ấm áp 2020 của sinh viên Đại học FPT</h4>
                                                            </div>
                                                            <div class="card-body">
                                                                <p class="card-text">Giải bóng đá Xuân ấm áp 2020 là sân chơi cho các bạn sinh viên Đại học FPT đam mê bóng đá trong dịp tết đến, xuân về. Toàn bộ số tiền lệ phí tham gia và tài trợ sẽ được dùng để tặng quà cho những người vô gia cư tại TP. Hồ Chí Minh.</p>
                                                                <a href="#" class="btn btn-primary">Xem thêm</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     /.col-md-6 
                                                    <div class="col-lg-4">
                                                        <div class="card card-primary card-outline">
                                                            <div class="card-header">
                                                                <h4 class="card-title m-0" style="font-size: 1.5rem">Hấp dẫn giải bóng đá Xuấn ấm áp 2020 của sinh viên Đại học FPT</h4>
                                                            </div>
                                                            <div class="card-body">
                                                                <p class="card-text">Giải bóng đá Xuân ấm áp 2020 là sân chơi cho các bạn sinh viên Đại học FPT đam mê bóng đá trong dịp tết đến, xuân về. Toàn bộ số tiền lệ phí tham gia và tài trợ sẽ được dùng để tặng quà cho những người vô gia cư tại TP. Hồ Chí Minh.</p>
                                                                <a href="#" class="btn btn-primary">Xem thêm</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                     /.col-md-6 
                                                </div>-->
                        <!-- /.row -->
                    </div><!-- /.container-fluid -->
                </div>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Control sidebar content goes here -->
                <div class="p-3">
                    <h5>Title</h5>
                    <p>Sidebar content</p>
                </div>
            </aside>
            <!-- /.control-sidebar -->

            <!-- Main Footer -->
            <footer class="main-footer">
                <!-- To the right -->
                <div class="float-right d-none d-sm-inline">
                    Anything you want
                </div>
                <!-- Default to the left -->
                <strong>Copyright &copy; 2020 <a href="https://adminlte.io">Huynh Kim Tri</a>.</strong> All rights reserved.
            </footer>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED SCRIPTS -->
        <!-- jQuery -->
        <script src="plugins/jquery/jquery.min.js"></script>
        <!-- Bootstrap 4 -->
        <script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- AdminLTE App -->
        <script src="dist/js/adminlte.min.js"></script>
    </body>
</html>
